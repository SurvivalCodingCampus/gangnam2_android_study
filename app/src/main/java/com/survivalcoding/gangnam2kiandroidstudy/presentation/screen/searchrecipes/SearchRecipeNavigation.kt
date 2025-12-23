package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

sealed interface SearchRecipeNavigation {
    data class RecipeDetails(val recipeId: Long) : SearchRecipeNavigation
    data object Back : SearchRecipeNavigation
}