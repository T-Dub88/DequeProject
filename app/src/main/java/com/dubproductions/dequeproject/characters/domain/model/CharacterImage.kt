package com.dubproductions.dequeproject.characters.domain.model

import com.squareup.moshi.Json

data class CharacterImage(
    @Json(name = "medium_url")
    val thumbUrl: String?
)
