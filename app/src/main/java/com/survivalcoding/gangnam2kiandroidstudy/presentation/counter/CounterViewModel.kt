package com.survivalcoding.gangnam2kiandroidstudy.presentation.counter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class CounterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CounterState())
    val uiState = _uiState.asStateFlow()

    /**
     * 카운트를 1 증가시키는 함수
     */
    fun increment() {
        _uiState.value = _uiState.value.copy(value = _uiState.value.value + 1)
    }

    /**
     * 카운트를 1 감소시키는 함수
     */
    fun decrement() {
        _uiState.value = _uiState.value.copy(value = _uiState.value.value - 1)
    }
}
