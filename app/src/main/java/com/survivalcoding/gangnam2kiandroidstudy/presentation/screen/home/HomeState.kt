package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NewRecipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.selector.HomeCategory

data class HomeState(
    val recipes: List<Recipe> = emptyList(),
    val selectedCategory: HomeCategory = HomeCategory.ALL,
    val filteredRecipes: List<Recipe> = emptyList(),
    val newRecipes: List<NewRecipe> = emptyList(),
    val bookmarkedIds: Set<Int> = emptySet(),
)