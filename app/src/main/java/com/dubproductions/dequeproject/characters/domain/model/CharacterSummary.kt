package com.dubproductions.dequeproject.characters.domain.model

import com.squareup.moshi.Json

data class CharacterSummary(
    val deck: String?,
    @Json(name = "first_appeared_in_game")
    val firstGame: Game?,
    val gender: Int?,
    val guid: String?,
    val image: CharacterImage?,
    val name: String?
)
