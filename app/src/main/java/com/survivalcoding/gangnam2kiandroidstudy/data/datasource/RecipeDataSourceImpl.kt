package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipesResponse
import kotlinx.serialization.json.Json

class RecipeDataSourceImpl private constructor(
    appAssetManager: AppAssetManager
) : RecipeDataSource {
    val recipesJsonString = appAssetManager.open("recipes.json").use { input ->
        input.readBytes().toString(Charsets.UTF_8)
    }

    override fun getRecipes(): List<Recipe> {
        val response = Json.decodeFromString<RecipesResponse>(recipesJsonString)
        return response.recipes
    }

    companion object {
        @Volatile private var instance: RecipeDataSource? = null

        fun getInstance(appAssetManager: AppAssetManager) =
            instance ?: synchronized(this) {
                instance ?: RecipeDataSourceImpl(appAssetManager).also { instance = it }
            }
    }
}