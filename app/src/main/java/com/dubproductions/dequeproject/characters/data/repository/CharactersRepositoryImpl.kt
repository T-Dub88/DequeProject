package com.dubproductions.dequeproject.characters.data.repository

import android.util.Log
import com.dubproductions.dequeproject.characters.data.model.CharacterDetails
import com.dubproductions.dequeproject.characters.data.remote.CharactersApiService
import com.dubproductions.dequeproject.characters.domain.model.CharacterSummary
import com.dubproductions.dequeproject.characters.domain.network.ScreenState
import com.dubproductions.dequeproject.characters.domain.repository.CharactersRepository
import retrofit2.HttpException
import java.io.IOException

class CharactersRepositoryImpl(
    private val charactersApiService: CharactersApiService
) : CharactersRepository {

    override suspend fun getCharactersList(pageNum: Int, pageSize: Int): ScreenState<List<CharacterSummary>> {
        val response = try {
            charactersApiService.getCharacters(
                limit = pageSize,
                offset = pageNum * pageSize
            )
        } catch (e: IOException) {
            return ScreenState.Error(errorMessage = "Network Connection Error")
        } catch (e: HttpException) {
            return ScreenState.Error(errorMessage = "HTTP Error: ${e.message}")
        } catch (e: Exception) {
            return ScreenState.Error(errorMessage = "Unknown Error: ${e.message}")
        }

        Log.i("Network Response", "getCharactersList: ${response.statusCode} ")

        return if (response.statusCode == 1) {
            ScreenState.Success(data = response.results ?: listOf())
        } else {
            ScreenState.Error(errorMessage = response.error ?: "Unknown Error")
        }

    }

    override suspend fun getCharacterDetails(id: String): ScreenState<CharacterDetails> {
        val response = try {
            charactersApiService.getCharacterDetails(id)
        } catch (e: IOException) {
            return ScreenState.Error(errorMessage = "Network Connection Error")
        } catch (e: HttpException) {
            return ScreenState.Error(errorMessage = "HTTP Error: ${e.message}")
        } catch (e: Exception) {
            return ScreenState.Error(errorMessage = "Unknown Error: ${e.message}")
        }

        return if (response.statusCode == 1) {
            ScreenState.Success(data = response.results ?: CharacterDetails())
        } else {
            ScreenState.Error(errorMessage = response.error ?: "Unknown Error")
        }

    }

}