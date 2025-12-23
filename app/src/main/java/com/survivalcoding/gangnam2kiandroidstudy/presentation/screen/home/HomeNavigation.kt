package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

sealed interface HomeNavigation {
    data object SearchRecipes : HomeNavigation
    data class RecipeDetails(val recipeId: Long) : HomeNavigation
}