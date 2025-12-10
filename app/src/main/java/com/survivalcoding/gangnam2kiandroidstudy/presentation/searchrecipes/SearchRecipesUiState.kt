package com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

data class SearchRecipesUiState(
    val searchKeyword: String = "",
    val isLoading: Boolean = false,
    val isShowBottomSheet: Boolean = false,
    val allRecipes: List<Recipe> = emptyList(),
    val filteredRecipes: List<Recipe> = emptyList(),
    val filterSearchState: FilterSearchState = FilterSearchState(),
    val message: String? = null
)