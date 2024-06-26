package com.dubproductions.dequeproject.characters.di

import com.dubproductions.dequeproject.characters.data.remote.CharactersApiService
import com.dubproductions.dequeproject.characters.data.repository.CharactersRepositoryImpl
import com.dubproductions.dequeproject.characters.domain.repository.CharactersRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharactersModule {

    @Provides
    @Singleton
    fun provideCharactersApi(): CharactersApiService {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://www.giantbomb.com/api/")
            .build()
            .create(CharactersApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCharactersRepository(
        charactersApiService: CharactersApiService
    ): CharactersRepository {
        return  CharactersRepositoryImpl(charactersApiService)
    }

}
