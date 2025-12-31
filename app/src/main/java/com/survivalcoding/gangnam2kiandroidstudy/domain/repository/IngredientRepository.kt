package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredient

interface IngredientRepository {
    suspend fun getIngredients(recipeId: Int): List<RecipeIngredient>
}
