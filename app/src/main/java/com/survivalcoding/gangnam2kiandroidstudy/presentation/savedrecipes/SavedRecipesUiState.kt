package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

data class SavedRecipesUiState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val message: String? = null
)
