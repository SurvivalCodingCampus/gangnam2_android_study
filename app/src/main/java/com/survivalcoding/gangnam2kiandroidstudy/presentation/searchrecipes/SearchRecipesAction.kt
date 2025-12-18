package com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes

import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchState

sealed interface SearchRecipesAction {
    data object OnTapFilterButton : SearchRecipesAction
    data class OnSearchRecipes(val query: String) : SearchRecipesAction
    data class OnUpdateFilterSearchState(val filterState: FilterSearchState) : SearchRecipesAction
}

//onSearchRecipes: (String) -> Unit = {},
//onTapFilterButton: () -> Unit = {},
//onUpdateFilterSearchState: (FilterSearchState) -> Unit = {},