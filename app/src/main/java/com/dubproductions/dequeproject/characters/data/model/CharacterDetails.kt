package com.dubproductions.dequeproject.characters.data.model

import com.dubproductions.dequeproject.characters.domain.model.CharacterImage
import com.dubproductions.dequeproject.characters.domain.model.Game
import com.squareup.moshi.Json

data class CharacterDetails(
    val aliases: String? = null,
    val birthday: String? = null,
    val deck: String? = null,
    @Json(name = "first_appeared_in_game")
    val firstGame: String? = null,
    val games: List<Game>? = null,
    val gender: Int? = null,
    val guid: String? = null,
    val image: CharacterImage? = null,
    @Json(name = "last_name")
    val lastName: String? = null,
    val name: String? = null,
    @Json(name = "real_name")
    val realName: String? = null,
    @Json(name = "site_detail_url")
    val siteDetailUrl: String? = null
)
