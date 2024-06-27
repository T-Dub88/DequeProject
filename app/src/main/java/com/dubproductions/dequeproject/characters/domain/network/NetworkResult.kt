package com.dubproductions.dequeproject.characters.domain.network

import com.dubproductions.dequeproject.characters.domain.model.CharacterSummary

sealed class NetworkResult<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Loading<T> : NetworkResult<T>()
    class Error<T>(errorMessage: String) : NetworkResult<T>(errorMessage = errorMessage)
}


