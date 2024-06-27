package com.dubproductions.dequeproject.characters.data.remote

import com.dubproductions.dequeproject.characters.data.model.ApiCharacterDetailsResponse
import com.dubproductions.dequeproject.characters.data.model.ApiCharacterListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_KEY = "ea98adc584efb356fcd14b949f8a9f2aa2b270b8"

interface CharactersApiService {

    @GET("characters/?api_key=$API_KEY&format=json")
    suspend fun getCharacters(@Query("offset") offset: Int, @Query("limit") limit: Int): ApiCharacterListResponse

    @GET("character/{guid}/?api_key=$API_KEY&format=json")
    suspend fun getCharacterDetails(@Path("guid") guid: String): ApiCharacterDetailsResponse

}
