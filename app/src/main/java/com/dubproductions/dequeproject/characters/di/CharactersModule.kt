package com.dubproductions.dequeproject.characters.di

import com.dubproductions.dequeproject.characters.data.remote.CharactersApi
import com.dubproductions.dequeproject.characters.data.repository.CharactersRepositoryImpl
import com.dubproductions.dequeproject.characters.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharactersModule {

    @Provides
    @Singleton
    fun provideCharactersApi(): CharactersApi {
        return Retrofit.Builder()
            .baseUrl("")
            .build()
            .create(CharactersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCharactersRepository(
        charactersApi: CharactersApi
    ): CharactersRepository {
        return  CharactersRepositoryImpl(charactersApi)
    }

}
