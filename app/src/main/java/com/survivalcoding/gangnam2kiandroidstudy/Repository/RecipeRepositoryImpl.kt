package com.survivalcoding.gangnam2kiandroidstudy.Repository

import com.survivalcoding.gangnam2kiandroidstudy.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.model.RecipeCard

class RecipeRepositoryImpl(
    private val recipeDataSource: RecipeDataSource
) : RecipeRepository {
    override suspend fun getRecipes(): List<RecipeCard> {
        return recipeDataSource.getRecipes().map { it.toModel() }
    }
}