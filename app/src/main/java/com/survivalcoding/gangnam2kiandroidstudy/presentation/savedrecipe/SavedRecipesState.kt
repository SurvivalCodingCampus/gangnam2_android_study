package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipe

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

data class SavedRecipesState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
)
