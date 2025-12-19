package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

sealed interface SearchRecipesEvent {
    data class ShowSnackBar(val message: String) : SearchRecipesEvent

    data class NavigateToRecipeDetail(val recipeId: Int) : SearchRecipesEvent

    data object NavigateBack : SearchRecipesEvent
}