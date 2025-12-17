package com.survivalcoding.gangnam2kiandroidstudy.data.data_source

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDTO

interface RecipeDataSource {
    suspend fun getRecipe(id: Long): RecipeDTO?
    suspend fun getRecipes(): List<RecipeDTO?>?
}