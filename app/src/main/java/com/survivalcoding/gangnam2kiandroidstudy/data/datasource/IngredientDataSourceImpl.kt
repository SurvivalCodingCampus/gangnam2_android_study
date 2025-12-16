package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.data.model.IngredientEntity
import com.survivalcoding.gangnam2kiandroidstudy.data.model.IngredientsResponse
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeIngredientEntity
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipesIngredientsResponse
import kotlinx.serialization.json.Json

class IngredientDataSourceImpl private constructor(
    private val appAssetManager: AppAssetManager
) : IngredientDataSource {
    val recipesIngredientsJsonString = appAssetManager.open("recipesIngredients.json").use { input ->
        input.readBytes().toString(Charsets.UTF_8)
    }

    val ingredientsJsonString = appAssetManager.open("ingredients.json").use { input ->
        input.readBytes().toString(Charsets.UTF_8)
    }

    override fun getRecipeIngredientsByRecipeId(recipeId: Int): List<RecipeIngredientEntity> {
        val response = Json.decodeFromString<RecipesIngredientsResponse>(recipesIngredientsJsonString)
        return response.recipesIngredients
            .filter { it.recipeId == recipeId }
    }

    override fun getAllIngredients(): List<IngredientEntity> {
        val response = Json.decodeFromString<IngredientsResponse>(ingredientsJsonString)
        return response.ingredients
    }

    companion object {
        @Volatile private var instance: IngredientDataSource? = null

        fun getInstance(appAssetManager: AppAssetManager) =
            instance ?: synchronized(this) {
                instance ?: IngredientDataSourceImpl(appAssetManager).also { instance = it }
            }
    }
}
