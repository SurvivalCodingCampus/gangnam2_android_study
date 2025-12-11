package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

interface RecipeRepository {
    suspend fun getRecipes(): List<Recipe>
}