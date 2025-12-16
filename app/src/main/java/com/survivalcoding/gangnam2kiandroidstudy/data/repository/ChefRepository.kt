package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Chef

interface ChefRepository {
    suspend fun getChefByName(name: String): Result<Chef>
}