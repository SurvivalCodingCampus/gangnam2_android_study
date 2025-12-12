package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.selector.HomeCategory

data class HomeState(
    val recipes: List<Recipe> = emptyList(),
    val selectedCategory: HomeCategory = HomeCategory.ALL,
    val filteredRecipes: List<Recipe> = emptyList()
)