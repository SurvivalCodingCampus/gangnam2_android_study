package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

data class SavedRecipesUiState(
    val recipes: List<Recipe> = emptyList(),
)
