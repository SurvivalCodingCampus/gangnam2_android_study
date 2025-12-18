package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.chef.Chef

interface ChefRepository {
    suspend fun getChefs(): List<Chef>
}
