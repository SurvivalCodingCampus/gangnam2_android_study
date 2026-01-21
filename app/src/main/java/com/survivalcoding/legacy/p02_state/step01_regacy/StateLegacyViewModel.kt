package com.survivalcoding.legacy.p02_state.step01_regacy

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class StateLegacyViewModel(val savedStateHandle: SavedStateHandle) : ViewModel() {
    var count = 0

    init {
        count = savedStateHandle.get<Int>("count") ?: 0 // 복원
    }

    fun increment() {
        count++
        savedStateHandle["count"] = count   // 저장
    }
}
