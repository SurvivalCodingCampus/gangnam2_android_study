package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.FilterSearchState

sealed interface SearchRecipesAction {
    data class KeywordChanged(val keyword: String) : SearchRecipesAction

    data object FilterClicked : SearchRecipesAction

    data object FilterDismissed : SearchRecipesAction

    data class FilterApplied(val filter: FilterSearchState) : SearchRecipesAction

    data class RecipeClicked(val recipeId: Int) : SearchRecipesAction

    data object FocusRequested : SearchRecipesAction
}