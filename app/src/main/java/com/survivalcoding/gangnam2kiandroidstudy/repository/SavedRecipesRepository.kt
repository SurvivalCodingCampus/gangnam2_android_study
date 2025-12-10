package com.survivalcoding.gangnam2kiandroidstudy.repository

import com.survivalcoding.gangnam2kiandroidstudy.model.Recipe

interface SavedRecipesRepository {
    suspend fun getSavedRecipes(): List<Recipe>
}