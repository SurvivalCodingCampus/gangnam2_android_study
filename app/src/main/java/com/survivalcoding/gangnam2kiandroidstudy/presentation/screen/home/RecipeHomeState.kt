package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeCategory

data class RecipeHomeState(
    val recipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val query: String = "",
    val selectedCategory: RecipeCategory = RecipeCategory.ALL,
)
