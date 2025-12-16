package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SavedRecipesViewModel(
    private val recipeRepository: RecipeRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _savedRecipes: MutableStateFlow<List<Recipe>> = MutableStateFlow(listOf())
    val savedRecipes: StateFlow<List<Recipe>> = _savedRecipes.asStateFlow()

    init {
        println("MainViewModel init")
        viewModelScope.launch {
            loadRecipes()
        }
    }

    suspend fun loadRecipes() {
        when (val response = recipeRepository.findRecipes()) {
            is Result.Success -> _savedRecipes.value = response.data
            is Result.Error -> println("에러 처리")
        }
    }

    suspend fun saveNewRecipe(id: Long) {
        val updated = _savedRecipes.value.toMutableList()

        when (val response = recipeRepository.findRecipe(id)) {
            is Result.Success -> updated.add(response.data)
            is Result.Error -> println("에러 처리")
        }

        _savedRecipes.value = updated
    }

    // 파괴될 때
    override fun onCleared() {
        println("MainViewModel cleared")
        super.onCleared()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val recipeRepository = (this[APPLICATION_KEY] as AppApplication).recipeRepository
                SavedRecipesViewModel(
                    recipeRepository = recipeRepository,
                    savedStateHandle = savedStateHandle,
                )
            }
        }
    }
}