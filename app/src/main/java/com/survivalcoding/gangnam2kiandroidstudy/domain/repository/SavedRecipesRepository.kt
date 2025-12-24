package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

interface SavedRecipesRepository {
    suspend fun getSavedRecipes(): List<Recipe>
    suspend fun deleteSavedRecipe(id: Int)
}