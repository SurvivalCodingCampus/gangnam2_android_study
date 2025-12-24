package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe

import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes.SearchRecipesEvent

sealed interface SavedRecipesEvent {

    data class NavigateToRecipeDetail(val recipeId: Int) : SavedRecipesEvent

    data class ShowSnackBar(val message: String) : SavedRecipesEvent
}