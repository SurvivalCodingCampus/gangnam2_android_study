package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

data class SearchRecipeState(
    var searchQuery: String = "",
    val searchText: String = "Recent Search",
    val recipes: List<Recipe> = emptyList(),
    val filteredRecipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
)
