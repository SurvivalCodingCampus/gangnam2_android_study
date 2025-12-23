package com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes

sealed interface SearchRecipesAction {
    data class OnSearchQueryChange(val query: String) : SearchRecipesAction
    data object OnBackClick : SearchRecipesAction
    data class OnFilterClick(val filter: FilterSearchState) : SearchRecipesAction
}
