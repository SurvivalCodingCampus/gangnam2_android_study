package com.survivalcoding.gangnam2kiandroidstudy.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.Hero
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.DataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    val repository: DataRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(Hero("홍길동", 10))
    val state = _state.asStateFlow()

    init {
        println("MainViewModel init")

        viewModelScope.launch {
            // 오래 걸리는 일
            _state.value = _state.value.copy(
                name = "이순신"
            )

            // 요즘 있어보이는 것
            _state.update {
                state.value.copy(
                    name = "이순신"
                )
            }
        }
    }

    // 파괴될 때
    override fun onCleared() {
        println("MainViewModel cleared")
        super.onCleared()
    }

    companion object {

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = (this[APPLICATION_KEY] as AppApplication).dataRepository
                MainViewModel(repository)
            }
        }
    }
}
