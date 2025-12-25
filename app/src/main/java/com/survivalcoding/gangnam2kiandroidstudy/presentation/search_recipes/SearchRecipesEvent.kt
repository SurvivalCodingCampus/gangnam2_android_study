package com.survivalcoding.gangnam2kiandroidstudy.presentation.search_recipes

sealed interface SearchRecipesEvent {
    data class ShowSnackbar(val message: String) : SearchRecipesEvent
    data object GoBack : SearchRecipesEvent
    data class NavigateToRecipeDetail(val recipeId: Int) : SearchRecipesEvent
}
