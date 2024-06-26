package com.dubproductions.dequeproject.characters.presentation.characters

import androidx.lifecycle.ViewModel
import com.dubproductions.dequeproject.characters.domain.repository.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

}