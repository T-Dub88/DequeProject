package com.dubproductions.dequeproject.characters.data.repository

import com.dubproductions.dequeproject.characters.data.remote.CharactersApi
import com.dubproductions.dequeproject.characters.domain.repository.CharactersRepository

class CharactersRepositoryImpl(
    val charactersApi: CharactersApi
) : CharactersRepository {
    override suspend fun getCharactersList() {
        TODO("Not yet implemented")
    }

}