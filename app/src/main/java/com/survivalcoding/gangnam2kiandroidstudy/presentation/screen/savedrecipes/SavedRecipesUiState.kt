package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

import androidx.compose.runtime.Immutable
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

@Immutable
data class SavedRecipesUiState(
    val recipes: List<Recipe> = emptyList(),
)
