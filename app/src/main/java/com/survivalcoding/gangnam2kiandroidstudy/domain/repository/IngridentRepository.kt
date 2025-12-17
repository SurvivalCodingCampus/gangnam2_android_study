package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingrident

interface IngridentRepository {
    suspend fun getIngrident(recipeId: Int): List<Ingrident>
}