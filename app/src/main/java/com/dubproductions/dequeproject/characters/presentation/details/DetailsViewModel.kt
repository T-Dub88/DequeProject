package com.dubproductions.dequeproject.characters.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dubproductions.dequeproject.characters.data.model.CharacterDetails
import com.dubproductions.dequeproject.characters.domain.model.CharacterSummary
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
class DetailsViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val _networkDataState = MutableStateFlow<NetworkResult<CharacterDetails>>(NetworkResult.Loading())
    val networkDataState = _networkDataState.asStateFlow()

    private fun updateNetworkDataState(newState: NetworkResult<CharacterDetails>) {
        _networkDataState.update {
            newState
        }
    }

    fun getCharacterDetails(id: String) {
        viewModelScope.launch {
            val result = async {
                charactersRepository.getCharacterDetails(id)
            }.await()

            updateNetworkDataState(result)
        }
    }

}
