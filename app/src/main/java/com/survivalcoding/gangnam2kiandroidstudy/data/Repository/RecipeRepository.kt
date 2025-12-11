package com.survivalcoding.gangnam2kiandroidstudy.data.Repository

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

interface RecipeRepository {
    suspend fun getRecipes(): List<Recipe>
}
