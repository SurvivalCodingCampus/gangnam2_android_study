package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import com.survivalcoding.gangnam2kiandroidstudy.model.Recipe

data class SearchRecipesState(
    val searchInputText: String = "",
    val resultRecipes: List<Recipe> = emptyList(),
    val selectedTime: String = "",
    val selectedRate: String = "",
    val selectedCategory: String = ""
    )