package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedRecipesScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedRecipesViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes = _recipes.asStateFlow()

    fun fetchRecipes() {
        viewModelScope.launch {
            recipeRepository.getAllRecipes()
                .onSuccess { recipes ->
                    _recipes.update { recipes }
                }
                .onFailure {
                    // TODO Error 처리
                    // UIState홀더 패턴을 배우면 처리할 예정
                }
        }
    }

    init {
        fetchRecipes()
    }
}