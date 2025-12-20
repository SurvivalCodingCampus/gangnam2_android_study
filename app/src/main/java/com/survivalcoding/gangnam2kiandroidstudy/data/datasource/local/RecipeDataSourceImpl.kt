package com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AppAssetManager
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.dto.RecipesResponse
import kotlinx.serialization.json.Json

class RecipeDataSourceImpl(
    appAssetManager: AppAssetManager
) : RecipeDataSource {
    val recipesJsonString = appAssetManager.open("recipes.json").use { input ->
        input.readBytes().toString(Charsets.UTF_8)
    }

    override fun getRecipes(): List<Recipe> {
        val response = Json.Default.decodeFromString<RecipesResponse>(recipesJsonString)
        return response.recipes
    }
}