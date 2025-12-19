package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

import androidx.compose.runtime.Immutable
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeSearchFilter

@Immutable
data class SearchRecipesUiState(
    val recipes: List<Recipe> = emptyList(),
    val searchText: String = "",
    val isLoading: Boolean = false,
    val isSearched: Boolean = false,
    val isSheetVisible: Boolean = false,
    val searchFilter: RecipeSearchFilter = RecipeSearchFilter(),
)