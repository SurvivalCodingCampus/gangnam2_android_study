package com.survivalcoding.gangnam2kiandroidstudy.presentation.counter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CounterViewModel : ViewModel() {
    private var _count = MutableStateFlow(0)

    val count = _count.asStateFlow()

    init {
        println("CounterViewModel: init")
    }

    override fun onCleared() {
        println("CounterViewModel: onCleared")
        super.onCleared()
    }

    fun increment() {
        _count.value++
    }
}

