package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.common.util.suspendRunCatching
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

class RecipeRepositoryImpl private constructor(
    private val recipeDataSource: RecipeDataSource
) : RecipeRepository {
    override suspend fun getAllRecipes(): Result<List<Recipe>> {
        return suspendRunCatching {
            recipeDataSource.getRecipes()
        }
    }

    companion object {
        @Volatile private var instance: RecipeRepository? = null

        fun getInstance(recipeDataSource: RecipeDataSource) =
            instance ?: synchronized(this) {
                instance ?: RecipeRepositoryImpl(recipeDataSource).also { instance = it }
            }
    }
}