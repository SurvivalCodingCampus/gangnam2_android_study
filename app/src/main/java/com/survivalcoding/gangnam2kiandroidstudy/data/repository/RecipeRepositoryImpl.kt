package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val recipeDataSource: RecipeDataSource
) : RecipeRepository {
    override suspend fun getRecipes(): List<Recipe> {
        return recipeDataSource.getRecipes().map { it.toModel() }
    }

    override suspend fun getRecipeById(id: Int): Recipe? {
        return recipeDataSource
            .getRecipes()
            .firstOrNull { it.id == id }
            ?.toModel()
    }

    override suspend fun getRecipesByIds(ids: List<Int>): List<Recipe> {
        if (ids.isEmpty()) return emptyList()

        return recipeDataSource
            .getRecipes()
            .filter { it.id in ids }
            .map { it.toModel() }
    }
}