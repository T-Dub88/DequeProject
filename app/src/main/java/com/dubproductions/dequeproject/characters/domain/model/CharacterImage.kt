package com.dubproductions.dequeproject.characters.domain.model

import com.squareup.moshi.Json

data class CharacterImage(
    @Json(name = "thumb_url")
    val thumbUrl: String?
)
