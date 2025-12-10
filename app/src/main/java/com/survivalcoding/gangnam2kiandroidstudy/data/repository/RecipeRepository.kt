package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.exception.NetworkError

interface RecipeRepository {
    suspend fun findAll(): Result<List<Recipe>, NetworkError>
    suspend fun search(query: String, time: String, rate: Double, category: String): Result<List<Recipe>, NetworkError>
}