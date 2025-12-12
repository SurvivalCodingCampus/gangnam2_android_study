package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.RecipeAppApplication
import com.survivalcoding.gangnam2kiandroidstudy.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.repository.SavedRecipesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val savedRecipesRepository: SavedRecipesRepository

) : ViewModel() {
    private var cachedRecipes: List<Recipe> = emptyList()

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            cachedRecipes = savedRecipesRepository.getSavedRecipes()
            _state.value =
                _state.value.copy(selectedCategory = "All", resultRecipes = cachedRecipes)
        }
        Log.d("HomeViewModel", "init: ${_state.value}")
    }

    fun onSelectedCategory(category: String) {

        if (category == "All") {
            _state.value =
                _state.value.copy(selectedCategory = category, resultRecipes = cachedRecipes)
        }

        else {
            _state.value = _state.value.copy(
                selectedCategory = category,
                resultRecipes = cachedRecipes.filter { it.category == category })
        }



        Log.d("HomeViewModel", "onSelectedCategory: ${_state.value}")
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedRecipesRepository =
                    (this[APPLICATION_KEY] as RecipeAppApplication).savedRecipesRepository
                HomeViewModel(
                    savedRecipesRepository = savedRecipesRepository,
                )
            }
        }
    }
}