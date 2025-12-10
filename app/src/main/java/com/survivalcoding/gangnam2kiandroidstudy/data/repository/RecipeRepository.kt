package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

interface RecipeRepository {
    suspend fun getAllRecipes(): Result<List<Recipe>>

    suspend fun getFilteredRecipes(keyword: String): Result<List<Recipe>>
}