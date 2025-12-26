package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

data class HomeState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val filteredRecipes: List<Recipe> = emptyList(),
    val selectedCategory: String = "",
    val bookmarkIds: List<Int> = emptyList(),
    val errorMessage: String? = null,
)
