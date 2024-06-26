package com.dubproductions.dequeproject.characters.data.repository

import com.dubproductions.dequeproject.characters.data.remote.CharactersApiService
import com.dubproductions.dequeproject.characters.domain.repository.CharactersRepository

class CharactersRepositoryImpl(
    private val charactersApiService: CharactersApiService
) : CharactersRepository {

    override suspend fun getCharactersList() {
        val response = charactersApiService.getCharacters()
        println(response.toString())
    }

}