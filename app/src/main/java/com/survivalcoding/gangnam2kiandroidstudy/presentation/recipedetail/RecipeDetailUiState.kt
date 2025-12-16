package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Chef
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeIngredient

data class RecipeDetailUiState(
    val isLoading: Boolean = false,
    val selectedTabPosition: Int = 0, // 0 = Ingredient, 1 = Procedure
    val recipe: Recipe = Recipe(0),
    val ingredients: List<RecipeIngredient> = emptyList(),
    val procedures: List<Procedure> = emptyList(),
    val chef: Chef? = null,
    val message: String? = null
)