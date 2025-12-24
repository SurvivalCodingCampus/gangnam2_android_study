package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail

sealed interface RecipeDetailsNavigation {
    data object Back : RecipeDetailsNavigation
    data class Reviews(val recipeId: Long) : RecipeDetailsNavigation
}