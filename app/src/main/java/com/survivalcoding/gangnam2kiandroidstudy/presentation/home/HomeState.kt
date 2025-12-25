package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.HomeRecipe

data class HomeState(
    val homeRecipes: List<HomeRecipe> = emptyList(),
    val filteredHomeRecipes: List<HomeRecipe> = emptyList(),
    val isLoading: Boolean = false,
    val selectedCategory: String = "All",
    val categories: List<String> = listOf("All")
)
