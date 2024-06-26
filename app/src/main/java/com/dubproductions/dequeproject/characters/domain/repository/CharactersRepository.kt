package com.dubproductions.dequeproject.characters.domain.repository

interface CharactersRepository {
    suspend fun getCharactersList()
}
