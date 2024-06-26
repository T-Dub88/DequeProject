package com.dubproductions.dequeproject.characters.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dubproductions.dequeproject.characters.domain.network.NetworkResult
import com.dubproductions.dequeproject.characters.domain.repository.CharactersRepository
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

    private val _networkDataState = MutableStateFlow<NetworkResult>(NetworkResult.Loading())
    val networkDataState = _networkDataState.asStateFlow()

    init {
        viewModelScope.launch {
            val result = async {
                charactersRepository.getCharactersList()
            }.await()
            updateNetworkDataState(result)
        }
    }

    private fun updateNetworkDataState(newState: NetworkResult) {
        _networkDataState.update {
            newState
        }
    }

}