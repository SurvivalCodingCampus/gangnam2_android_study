package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeRootDto

interface RecipeDataSource {
    suspend fun getRecipes(): RecipeRootDto
}