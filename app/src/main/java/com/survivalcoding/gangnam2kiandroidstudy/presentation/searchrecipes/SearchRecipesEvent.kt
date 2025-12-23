package com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes

sealed interface SearchRecipesEvent {
    data class NavigateToRecipeDetails(val recipeId: Long) : SearchRecipesEvent

}