package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe

sealed interface SearchRecipesAction {
    data object OnBackClick : SearchRecipesAction
    data class OnFilterClick(val filter: FilterSearchState) : SearchRecipesAction
    data class OnSearchTermChange(val searchTerm: String) : SearchRecipesAction
    data class OnRecipeCardClick(val recipeId: Long) : SearchRecipesAction
}