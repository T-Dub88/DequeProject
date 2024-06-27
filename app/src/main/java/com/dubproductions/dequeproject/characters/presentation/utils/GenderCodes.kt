package com.dubproductions.dequeproject.characters.presentation.utils

object GenderCodes {
    fun getGender(code: Int): String {
        return when(code) {
            1 -> "Male"
            2 -> "Female"
            else -> "Other"
        }
    }
}