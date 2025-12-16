package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeIngredientUI

interface IngredientRepository {
    suspend fun getIngredientByRecipeId(recipeId: Int): Result<List<RecipeIngredientUI>>
}