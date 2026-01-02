package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.savedRecipes.RecipesDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipesRepository

class RecipesRepositoryImpl(
    private val recipesDataSource: RecipesDataSource
) : RecipesRepository {
    override suspend fun getAllRecipes(): List<Recipe> {
        return recipesDataSource.getAllRecipes().recipes
    }

    override suspend fun deleteSavedRecipe(id: Int) {
        recipesDataSource.deleteSavedRecipe(id)
    }
}