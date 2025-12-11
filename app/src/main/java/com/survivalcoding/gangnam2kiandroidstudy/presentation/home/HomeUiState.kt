package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

data class HomeUiState(
    val isLoading: Boolean = false,
    val selectedCategory: String = "All",
    val recipes: List<Recipe> = emptyList(),
    val message: String? = null
)