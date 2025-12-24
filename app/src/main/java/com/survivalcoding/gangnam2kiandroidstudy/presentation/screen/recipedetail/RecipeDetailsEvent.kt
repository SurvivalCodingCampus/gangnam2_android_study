package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail

sealed interface RecipeDetailsEvent {
    data object NavigateToBack : RecipeDetailsEvent
    data class NavigateToReviews(val recipeId: Long) : RecipeDetailsEvent
}