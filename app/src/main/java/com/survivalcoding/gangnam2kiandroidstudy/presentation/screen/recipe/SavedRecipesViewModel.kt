package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.data.Repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedRecipesViewModel(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SavedRecipesState())
    val state: StateFlow<SavedRecipesState> = _state.asStateFlow()

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val recipes = repository.getRecipes()

            _state.update {
                it.copy(
                    recipes = recipes,
                    isLoading = false
                )
            }
        }
    }

    companion object {
        fun factory(application: AppApplication): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    SavedRecipesViewModel(
                        repository = application.recipeRepository
                    )
                }
            }
    }
}