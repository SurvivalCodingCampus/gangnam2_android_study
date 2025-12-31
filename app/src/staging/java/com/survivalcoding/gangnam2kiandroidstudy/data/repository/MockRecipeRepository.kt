package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class MockRecipeRepository : RecipeRepository {
    override suspend fun findAll(): Result<List<Recipe>, NetworkError> {
        return Result.Success(emptyList())
    }

    override suspend fun search(
        query: String,
        time: String,
        rate: Double,
        category: String
    ): Result<List<Recipe>, NetworkError> {
        return Result.Success(emptyList())
    }

    override suspend fun getRecipes(): List<Recipe> {
        return emptyList()
    }

    override suspend fun getRecipeById(recipeId: Int): Recipe? {
        return null
    }
}
