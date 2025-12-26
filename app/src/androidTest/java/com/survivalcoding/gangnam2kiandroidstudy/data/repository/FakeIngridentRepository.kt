package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingrident
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngridentRepository

class FakeIngridentRepository : IngridentRepository {
    override suspend fun getIngrident(recipeId: Int): List<Ingrident> {
        return listOf(
            Ingrident(100, "Tomato", 200, "url"),
            Ingrident(101, "Onion", 100, "url")
        )
    }
}
