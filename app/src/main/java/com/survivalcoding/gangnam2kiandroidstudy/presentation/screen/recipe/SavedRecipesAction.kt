package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe

sealed interface SavedRecipesAction {

    data class RecipeClicked(val recipeId: Int) : SavedRecipesAction

    data class BookmarkClicked(val recipeId: Int) : SavedRecipesAction

    data object ReachedBottom : SavedRecipesAction
}
