package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.RecipeAppApplication
import com.survivalcoding.gangnam2kiandroidstudy.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.repository.SavedRecipesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SavedRecipesViewModel(
    private val savedRecipesRepository: SavedRecipesRepository,
) : ViewModel() {

    private val _savedRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    val savedRecipes = _savedRecipes.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _savedRecipes.value = savedRecipesRepository.getSavedRecipes()
        }
    }

    companion object {
        fun factory(application: RecipeAppApplication): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    SavedRecipesViewModel(application.savedRecipesRepository)
                }
            }
    }
}