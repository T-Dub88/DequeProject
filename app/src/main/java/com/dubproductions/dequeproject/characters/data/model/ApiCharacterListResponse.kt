package com.dubproductions.dequeproject.characters.data.model

import com.dubproductions.dequeproject.characters.domain.model.CharacterSummary
import com.squareup.moshi.Json

data class ApiCharacterListResponse(
    val error: String?,
    @Json(name = "status_code")
    val statusCode: Int?,
    val results: List<CharacterSummary>?
)
