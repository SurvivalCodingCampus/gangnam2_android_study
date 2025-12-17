package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

data class SavedRecipesState(
    val recipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val bookmarkedIds: Set<Int> = emptySet(),
)