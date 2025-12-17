package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

interface RecipeRepository {
    suspend fun findRecipe(id: Long): Result<Recipe, String>
    suspend fun findRecipes(): Result<List<Recipe>, String>
}