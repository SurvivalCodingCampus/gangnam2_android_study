package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

sealed interface SearchRecipesAction {
    data object OnTapFilterButton : SearchRecipesAction
    data class OnSearchRecipes(val query: String) : SearchRecipesAction
    data class OnUpdateFilterSearchState(val filterState: FilterSearchState) : SearchRecipesAction
    data class SelectRecipes(val id: Int) : SearchRecipesAction
    data class OnCancelFilter(val filterState: FilterSearchState) : SearchRecipesAction

}