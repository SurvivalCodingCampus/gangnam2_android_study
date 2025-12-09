package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toRecipe
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

class RecipeRepositoryImpl(
    private val dataSource: RecipeDataSource
) : RecipeRepository {
    override suspend fun getRecipes(): List<Recipe> {
        return dataSource.getRecipes().recipes?.map { it.toRecipe() } ?: emptyList()
    }
}