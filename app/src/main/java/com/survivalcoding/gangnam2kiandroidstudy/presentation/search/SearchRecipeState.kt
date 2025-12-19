package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import androidx.compose.runtime.Immutable
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

@Immutable
data class SearchRecipeState(
    val searchQuery: String = "",
    val searchText: String = "Recent Search",
    val recipes: List<Recipe> = emptyList(),
    val filteredRecipes: List<Recipe> = emptyList(),
    val filteredRecipesText: String = "",
    val isLoading: Boolean = false,
    val filterState: FilterSearchState = FilterSearchState(),
    val showBottomSheet: Boolean = false,
)
