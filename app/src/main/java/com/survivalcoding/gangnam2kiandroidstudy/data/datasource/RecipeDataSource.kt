package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.core.Response
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeResponse

interface RecipeDataSource {
    suspend fun findAll(): Response<RecipeResponse>
}