package com.dubproductions.dequeproject.characters.presentation.nav

import kotlinx.serialization.Serializable

sealed class NavRoutes {
    @Serializable
    object CharactersScreen

    @Serializable
    data class DetailsScreen(
        val characterId: String
    )
}
