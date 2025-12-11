package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

class RecipeRepositoryImpl(
    private val recipeDataSource: RecipeDataSource,
) : RecipeRepository {

    override suspend fun getRecipes(): List<Recipe> {
        return recipeDataSource.getRecipes().map { it.toModel() }
    }
}