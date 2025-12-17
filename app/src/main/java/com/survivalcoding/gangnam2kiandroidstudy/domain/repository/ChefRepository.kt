package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Chef

interface ChefRepository {
    suspend fun getChefById(id: Int): Chef?
}
