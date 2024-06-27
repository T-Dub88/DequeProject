package com.dubproductions.dequeproject.characters.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dubproductions.dequeproject.characters.domain.repository.CharactersRepository
import com.dubproductions.dequeproject.characters.presentation.utils.CharacterPaginator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val _screenState = MutableStateFlow(CharactersScreenState())
    val screenState = _screenState.asStateFlow()

    private val paginator = CharacterPaginator(
        initialKey = 0,
        onLoadUpdated = {
            updateScreenState(
                screenState.value.copy(
                    isLoadingNewPage = it,
                    isLoadingInitialData = if (screenState.value.isLoadingInitialData) {
                        it
                    } else {
                        false
                    }
                )
            )
        },
        onRequest = { nextPage ->
            viewModelScope.async { charactersRepository.getCharactersList(
                pageNum = screenState.value.currentPage ,
                pageSize = 20
            ) }.await()
        },
        getNextKey = {
            screenState.value.currentPage + 1
        },
        onError = {
            updateScreenState(
                screenState.value.copy(
                    errorMessage = it
                )
            )
        },
        onSuccess = { items, newKey ->
            updateScreenState(
                newState = screenState.value.copy(
                    characterList = screenState.value.characterList + items,
                    currentPage = newKey,
                    endReached = items.isEmpty()
                )
            )
        }
    )

    init {
        viewModelScope.launch {
            loadNextItems()
        }
    }

    private fun updateScreenState(newState: CharactersScreenState) {
        _screenState.update {
            newState
        }
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

}