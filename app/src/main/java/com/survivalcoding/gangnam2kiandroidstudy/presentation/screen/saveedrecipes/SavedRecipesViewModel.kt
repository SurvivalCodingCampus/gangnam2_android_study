package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saveedrecipes

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
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RecipeUiState(
    val recipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
)

class SavedRecipesViewModel(
    private val recipeRepository: RecipeRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val recipes = recipeRepository.getRecipes()
            _uiState.value = _uiState.value.copy(recipes = recipes, isLoading = false)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val recipeRepository = (this[APPLICATION_KEY] as AppApplication).recipeRepository
                SavedRecipesViewModel(
                    recipeRepository,
                    savedStateHandle = savedStateHandle,
                )
            }
        }
    }

}
