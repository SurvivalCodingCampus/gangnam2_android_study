package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

data class SavedRecipesState(
    val recipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
)