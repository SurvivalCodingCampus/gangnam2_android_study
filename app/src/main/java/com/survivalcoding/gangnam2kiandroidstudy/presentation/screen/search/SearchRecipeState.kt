package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

import androidx.compose.runtime.Immutable
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search.filter.FilterSearchState

@Immutable
data class SearchRecipeState(
    val recipes: List<Recipe> = emptyList(),
    val filterRecipes: List<Recipe> = emptyList(),
    val query: String = "",
    val showBottomSheet: Boolean = false,
    val isLoading: Boolean = true,
    val error: String? = null,
    val isSearchEnabled: Boolean = true,
    val filterSearchState: FilterSearchState = FilterSearchState()
)
