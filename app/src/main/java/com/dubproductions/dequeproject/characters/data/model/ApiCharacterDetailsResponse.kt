package com.dubproductions.dequeproject.characters.data.model

import com.squareup.moshi.Json

data class ApiCharacterDetailsResponse(
    val error: String?,
    @Json(name = "status_code")
    val statusCode: Int?,
    val results: CharacterDetails?
)
