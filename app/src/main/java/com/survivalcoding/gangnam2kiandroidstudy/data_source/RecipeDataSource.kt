package com.survivalcoding.gangnam2kiandroidstudy.data_source

import com.survivalcoding.gangnam2kiandroidstudy.dto.RecipeDto

interface RecipeDataSource {
    suspend fun getRecipes(): List<RecipeDto>
}