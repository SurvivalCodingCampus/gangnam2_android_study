package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingrident

interface IngridentRepository {

    suspend fun getIngridents(id: Int): List<Ingrident>
}