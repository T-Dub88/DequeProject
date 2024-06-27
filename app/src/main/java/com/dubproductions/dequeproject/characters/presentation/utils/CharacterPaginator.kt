package com.dubproductions.dequeproject.characters.presentation.utils

import com.dubproductions.dequeproject.characters.domain.model.CharacterSummary
import com.dubproductions.dequeproject.characters.domain.network.ScreenState

class CharacterPaginator<Key>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> ScreenState<List<CharacterSummary>>,
    private inline val getNextKey: suspend (List<CharacterSummary>) -> Key,
    private inline val onError: suspend (String?) -> Unit,
    private inline val onSuccess: suspend (items: List<CharacterSummary>, newKey: Key) -> Unit
) {
    private var currentKey = initialKey
    private var isMakingRequest = false

    suspend fun loadNextItems() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        if(result.errorMessage != null) {
            onError(result.errorMessage)
            onLoadUpdated(false)
            return
        }
        result.data?.let {
            currentKey = getNextKey(it)
            onSuccess(it, currentKey)
        }

        onLoadUpdated(false)

    }


    fun reset() {
        currentKey = initialKey
    }

}
