package com.survivalcoding.gangnam2kiandroidstudy.data.data_source

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.ChefDto

interface ChefDataSource {
    suspend fun getChefs(): List<ChefDto>
    suspend fun getChefById(id: Int): ChefDto?
}
