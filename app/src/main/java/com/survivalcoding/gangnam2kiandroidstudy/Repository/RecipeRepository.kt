package com.survivalcoding.gangnam2kiandroidstudy.Repository

import com.survivalcoding.gangnam2kiandroidstudy.model.RecipeCard

interface RecipeRepository {
    suspend fun getRecipes(): List<RecipeCard>
}
