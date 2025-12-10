package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeSearchFilter

data class SearchRecipesUiState(
    val recipes: List<Recipe> = emptyList(),
    val searchText: String = "",
    val isLoading: Boolean = false,
    val isSheetVisible: Boolean = false,
    val searchFilter: RecipeSearchFilter = RecipeSearchFilter(),
) {
    val isSearched: Boolean
        get() = searchText.isNotBlank() || searchFilter.isNotNull()
}