package com.survivalcoding.gangnam2kiandroidstudy.data.data_source

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDto

interface RecipeDataSource {
    suspend fun getRecipes(): List<RecipeDto>
}