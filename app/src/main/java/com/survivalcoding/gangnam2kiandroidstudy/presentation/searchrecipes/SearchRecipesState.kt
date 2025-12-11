package com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchState

data class SearchRecipesState(
    val recipes: List<Recipe> = emptyList(),
    val filteredRecipes: List<Recipe> = emptyList(),
    val searchKeyword: String = "",
    val isLoading: Boolean = false,
    val showBottomSheet: Boolean = false,
    val filterSearchState: FilterSearchState = FilterSearchState(),
)