package com.dubproductions.dequeproject.characters.domain.repository

import com.dubproductions.dequeproject.characters.domain.network.NetworkResult

interface CharactersRepository {
    suspend fun getCharactersList(): NetworkResult
    suspend fun getCharacterDetails(id: String): NetworkResult
}
