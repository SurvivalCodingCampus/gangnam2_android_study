package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail

import androidx.compose.runtime.Immutable
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

@Immutable
data class RecipeDetailState(
    val recipe: Recipe? = null,
    val selectedTabIndex: Int = 0,
    val tabLabels: List<String> = listOf("Ingredient", "Procedure"),
    val error: String? = null
)
