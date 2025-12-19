package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe

sealed interface SearchRecipesEvent {
    data object NavigateToBack : SearchRecipesEvent
    data class NavigateToRecipeDetail(val recipeId: Long) : SearchRecipesEvent
    data object SnackBarApplyFilter : SearchRecipesEvent
    data object SnackBarCancelFilter : SearchRecipesEvent
}
