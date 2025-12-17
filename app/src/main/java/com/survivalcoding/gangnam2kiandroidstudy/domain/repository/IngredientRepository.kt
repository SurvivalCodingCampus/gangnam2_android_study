package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.IngredientItem

interface IngredientRepository {
    suspend fun getIngredientsByRecipeId(
        recipeId: Int,
    ): List<IngredientItem>
}