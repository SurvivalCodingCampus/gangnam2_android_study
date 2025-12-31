package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository

class MockIngredientRepository : IngredientRepository {
    override suspend fun getIngredients(id: Int): List<Ingredient> {
        return emptyList()
    }
}
