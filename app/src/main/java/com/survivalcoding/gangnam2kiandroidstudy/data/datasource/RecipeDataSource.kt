package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDto

interface RecipeDataSource {
    suspend fun getRecipes(): List<RecipeDto>
}