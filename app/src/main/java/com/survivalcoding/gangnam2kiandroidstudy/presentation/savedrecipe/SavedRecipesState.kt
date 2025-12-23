package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipe

import androidx.compose.runtime.Stable
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

@Stable
data class SavedRecipesState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
)
