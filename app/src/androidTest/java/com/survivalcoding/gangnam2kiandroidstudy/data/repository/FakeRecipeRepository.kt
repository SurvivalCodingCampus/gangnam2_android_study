package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import kotlinx.coroutines.delay

class FakeRecipeRepository : RecipeRepository {
    override suspend fun getRecipes(): List<Recipe> {
        delay(1000) // Simulate network delay
        return listOf(
            Recipe(
                title = "Traditional spare ribs baked",
                chef = "Chef John",
                time = "20 min",
                category = "Chinese",
                rating = 4.0,
                imageUrls = "https://cdn.pixabay.com/photo/2017/05/07/08/56/pancakes-2291908_960_720.jpg",
                createdAt = System.currentTimeMillis(),
                id = 1,
                address = "Seoul",
            ),
             Recipe(
                title = "Spice roasted chicken",
                chef = "Mark Kelvin",
                time = "20 min",
                category = "Chinese",
                rating = 4.0,
                imageUrls = "https://cdn.pixabay.com/photo/2017/05/07/08/56/pancakes-2291908_960_720.jpg",
                createdAt = System.currentTimeMillis(),
                id = 2,
                address = "Seoul",
            )
        )
    }
}
