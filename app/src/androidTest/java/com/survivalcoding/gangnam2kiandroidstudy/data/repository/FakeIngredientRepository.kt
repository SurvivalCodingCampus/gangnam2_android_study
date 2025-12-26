package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository

class FakeIngredientRepository : IngredientRepository {
    override suspend fun getIngredients(recipeId: Int): List<Ingredient> {
        return emptyList()
    }
}
