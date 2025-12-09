package com.survivalcoding.gangnam2kiandroidstudy.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.DataRepository

class MainViewModel(
    val repository: DataRepository,
) : ViewModel() {

    init {
        println("MainViewModel init")
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
