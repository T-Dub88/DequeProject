package com.dubproductions.dequeproject.characters.domain.network

sealed class ScreenState<T>(
    val data: T? = null,
    val errorMessage: String? = null,
    val initialPage: Int = 0
) {
    class Success<T>(data: T) : ScreenState<T>(data)
    class Loading<T> : ScreenState<T>()
    class Error<T>(errorMessage: String) : ScreenState<T>(errorMessage = errorMessage)
}


