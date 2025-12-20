package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class RecipeRepositoryImpl private constructor(
    private val recipeDataSource: RecipeDataSource
) : RecipeRepository {
    override suspend fun getAllRecipes(): List<Recipe> {
        return recipeDataSource.getRecipes()
    }

    override suspend fun getFilteredRecipes(keyword: String): List<Recipe> {
        return recipeDataSource.getRecipes().filter {
            val keywordLowercase = keyword.lowercase()

            it.name.lowercase().contains(keywordLowercase)
                    || it.chef.lowercase().contains(keywordLowercase)
        }
    }

    override suspend fun getFilteredRecipesByCategory(category: String): List<Recipe> {
        return if (category == "All") {
            recipeDataSource.getRecipes()
        } else {
            recipeDataSource.getRecipes().filter {
                it.category == category
            }
        }
    }

    override suspend fun getRecipeById(id: Int): Recipe? {
        return recipeDataSource.getRecipes().find { it.id == id }
    }

    companion object {
        @Volatile private var instance: RecipeRepository? = null

        fun getInstance(recipeDataSource: RecipeDataSource) =
            instance ?: synchronized(this) {
                instance ?: RecipeRepositoryImpl(recipeDataSource).also { instance = it }
            }
    }
}