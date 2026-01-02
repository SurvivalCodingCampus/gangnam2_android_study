package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

interface RecipesRepository {
    suspend fun getAllRecipes(): List<Recipe>
    suspend fun deleteSavedRecipe(id: Int)
}