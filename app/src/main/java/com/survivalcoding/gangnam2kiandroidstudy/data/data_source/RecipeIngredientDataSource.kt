package com.survivalcoding.gangnam2kiandroidstudy.data.data_source

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeIngredientDto

interface RecipeIngredientDataSource {
    suspend fun getIngredientsByRecipeId(recipeId: Int): List<RecipeIngredientDto>
}
