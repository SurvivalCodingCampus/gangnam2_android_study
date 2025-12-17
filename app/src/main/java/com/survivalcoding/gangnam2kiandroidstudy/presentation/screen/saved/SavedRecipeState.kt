package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

data class SavedRecipeState(
    val data: List<Recipe> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)