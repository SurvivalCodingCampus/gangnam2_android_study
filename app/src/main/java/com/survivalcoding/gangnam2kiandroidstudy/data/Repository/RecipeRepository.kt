package com.survivalcoding.gangnam2kiandroidstudy.data.Repository

import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeCard

interface RecipeRepository {
    suspend fun getRecipes(): List<RecipeCard>
}
