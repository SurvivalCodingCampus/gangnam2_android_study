package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search.filter.FilterSearchState

data class SearchRecipeState(
    val recipes: List<Recipe> = emptyList(),
    val filterRecipes: List<Recipe> = emptyList(),
    val query: String = "",
    val showBottomSheet: Boolean = false,
    val isLoading: Boolean = true,
    val error: String? = null,
    val filterSearchState: FilterSearchState = FilterSearchState()
)
