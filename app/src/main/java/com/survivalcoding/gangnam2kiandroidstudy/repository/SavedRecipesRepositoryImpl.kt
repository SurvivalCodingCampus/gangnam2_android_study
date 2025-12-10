package com.survivalcoding.gangnam2kiandroidstudy.repository

import com.survivalcoding.gangnam2kiandroidstudy.data_source.SavedRecipesDataSource
import com.survivalcoding.gangnam2kiandroidstudy.model.Recipe

class SavedRecipesRepositoryImpl(
    val savedRecipesDataSource: SavedRecipesDataSource
) : SavedRecipesRepository {
    override suspend fun getSavedRecipes(): List<Recipe> {


        return savedRecipesDataSource.getSavedRecipes().recipes
    }
}