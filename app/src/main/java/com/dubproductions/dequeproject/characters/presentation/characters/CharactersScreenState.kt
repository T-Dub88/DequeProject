package com.dubproductions.dequeproject.characters.presentation.characters

import com.dubproductions.dequeproject.characters.domain.model.CharacterSummary

data class CharactersScreenState(
    val isLoadingInitialData: Boolean = true,
    val isLoadingNewPage: Boolean = false,
    val errorMessage: String? = null,
    val characterList: List<CharacterSummary> = listOf(),
    val currentPage: Int = 0,
    val endReached: Boolean = false
)
