package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

sealed interface SearchRecipesAction {
    data object ToggleBottomSheet : SearchRecipesAction
    data class CancelFilterRecipes(
        val searchText: String,
        val time: String,
        val rate: String,
        val category: String,
        val enableBottomSheet: Boolean
    ) : SearchRecipesAction

    data class ApplyFilterRecipes(
        val searchText: String,
        val time: String,
        val rate: String,
        val category: String,
        val enableBottomSheet: Boolean
    ) : SearchRecipesAction

    data class ChangeSearch(
        val searchText: String,
        val time: String,
        val rate: String,
        val category: String,
        val enableBottomSheet: Boolean
    ): SearchRecipesAction

}