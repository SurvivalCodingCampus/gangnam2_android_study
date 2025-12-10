package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeSearchCondition

interface RecipeRepository {
    suspend fun getSavedRecipes(): Result<List<Recipe>, NetworkError>
    suspend fun getRecipes(searchCondition: RecipeSearchCondition): Result<List<Recipe>, NetworkError>
}