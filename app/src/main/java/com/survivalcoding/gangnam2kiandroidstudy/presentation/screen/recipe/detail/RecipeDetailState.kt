package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

data class RecipeDetailState(
    val recipe: Recipe? = null,
    val selectedTabIndex: Int = 0,
    val tabLabels: List<String> = listOf("Ingrident", "Procedure"),
    val error: String? = null
)
