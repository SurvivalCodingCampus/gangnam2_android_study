package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.savedRecipes.SavedRecipesDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.SavedRecipesRepository

class SavedRecipesRepositoryImpl(
    private val savedRecipesDataSource: SavedRecipesDataSource
) : SavedRecipesRepository {
    override suspend fun getSavedRecipes(): List<Recipe> {
        return savedRecipesDataSource.getSavedRecipes().recipes
    }


}