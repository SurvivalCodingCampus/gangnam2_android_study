package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

sealed interface SearchRecipesAction {
    data object ToggleBottomSheet : SearchRecipesAction
    data class FilterRecipes(
        val searchText: String,
        val time: String,
        val rate: String,
        val category: String,
        val enableBottomSheet: Boolean
    ) : SearchRecipesAction

}