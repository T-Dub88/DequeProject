package com.dubproductions.dequeproject.characters.domain.repository

import com.dubproductions.dequeproject.characters.data.model.CharacterDetails
import com.dubproductions.dequeproject.characters.domain.model.CharacterSummary
import com.dubproductions.dequeproject.characters.domain.network.ScreenState

interface CharactersRepository {
    suspend fun getCharactersList(pageNum: Int, pageSize: Int): ScreenState<List<CharacterSummary>>
    suspend fun getCharacterDetails(id: String): ScreenState<CharacterDetails>
}
