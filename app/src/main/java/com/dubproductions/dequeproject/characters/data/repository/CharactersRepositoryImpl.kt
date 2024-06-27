package com.dubproductions.dequeproject.characters.data.repository

import android.util.Log
import com.dubproductions.dequeproject.characters.data.model.CharacterDetails
import com.dubproductions.dequeproject.characters.data.remote.CharactersApiService
import com.dubproductions.dequeproject.characters.domain.model.CharacterSummary
import com.dubproductions.dequeproject.characters.domain.network.NetworkResult
import com.dubproductions.dequeproject.characters.domain.repository.CharactersRepository
import retrofit2.HttpException
import java.io.IOException

class CharactersRepositoryImpl(
    private val charactersApiService: CharactersApiService
) : CharactersRepository {

    override suspend fun getCharactersList(): NetworkResult<List<CharacterSummary>?> {
        val response = try {
            charactersApiService.getCharacters()
        } catch (e: IOException) {
            return NetworkResult.Error(errorMessage = "Network Connection Error")
        } catch (e: HttpException) {
            return NetworkResult.Error(errorMessage = "HTTP Error: ${e.message}")
        } catch (e: Exception) {
            return NetworkResult.Error(errorMessage = "Unknown Error: ${e.message}")
        }

        Log.i("Network Response", "getCharactersList: ${response.statusCode} ")

        return if (response.statusCode == 1) {
            NetworkResult.Success(data = response.results ?: listOf())
        } else {
            NetworkResult.Error(errorMessage = response.error ?: "Unknown Error")
        }

    }

    override suspend fun getCharacterDetails(id: String): NetworkResult<CharacterDetails> {
        val response = try {
            charactersApiService.getCharacterDetails(id)
        } catch (e: IOException) {
            return NetworkResult.Error(errorMessage = "Network Connection Error")
        } catch (e: HttpException) {
            return NetworkResult.Error(errorMessage = "HTTP Error: ${e.message}")
        } catch (e: Exception) {
            return NetworkResult.Error(errorMessage = "Unknown Error: ${e.message}")
        }

        return if (response.statusCode == 1) {
            NetworkResult.Success(data = response.results ?: CharacterDetails())
        } else {
            NetworkResult.Error(errorMessage = response.error ?: "Unknown Error")
        }

    }

}