package com.dubproductions.dequeproject.characters.domain.network

import com.dubproductions.dequeproject.characters.domain.model.CharacterSummary

sealed class NetworkResult(
    val data: List<CharacterSummary>? = null,
    val errorMessage: String? = null
) {
    class Success(data: List<CharacterSummary>) : NetworkResult(data)
    class Loading : NetworkResult()
    class Error(errorMessage: String) : NetworkResult(errorMessage = errorMessage)
}


