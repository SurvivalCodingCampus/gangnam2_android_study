package com.survivalcoding.gangnam2kiandroidstudy.presentation.ingredient

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

data class IngredientState(
    val recipe: Recipe? = null,
    val procedures: List<Procedure> = emptyList(),
    val selectedTab: String = "Ingredient",
    val isLoading: Boolean = false,
    val isMoreOptionsOpen: Boolean = false,
    val isShareDialogVisible: Boolean = false,
    val recipeLink: String = "https://www.example.com/recipe/123"
)
