package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

data class HomeState(
    val selectedCategory: String = "All",
    val resultRecipes: List<Recipe> = emptyList()
)
