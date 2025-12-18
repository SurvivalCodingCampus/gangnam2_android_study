package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.runtime.Immutable
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory

@Immutable
data class RecipeHomeState(
    val recipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val query: String = "",
    val selectedCategory: RecipeCategory = RecipeCategory.ALL,
    val isSearchEnabled: Boolean = false
)
