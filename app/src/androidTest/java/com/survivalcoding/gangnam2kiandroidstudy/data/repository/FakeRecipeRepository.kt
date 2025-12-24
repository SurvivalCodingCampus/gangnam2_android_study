package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class FakeRecipeRepository : RecipeRepository {

    private val fakeRecipes = listOf(
        Recipe(
            id = 1,
            name = "Traditional spare ribs baked",
            category = "Meat",
            rating = 4.5,
            time = "60 min",
            chef = "Chef John",
            image = "",
            ingredients = emptyList()
        ),
        Recipe(
            id = 2,
            name = "Lamb chops with fruity couscous and mint",
            category = "Meat",
            rating = 4.0,
            time = "45 min",
            chef = "Chef Emily",
            image = "",
            ingredients = emptyList()
        ),
        Recipe(
            id = 3,
            name = "Spice roasted chicken with flavored rice",
            category = "Chicken",
            rating = 4.8,
            time = "75 min",
            chef = "Chef Michael",
            image = "",
            ingredients = emptyList()
        ),
        Recipe(
            id = 4,
            name = "Chinese style Egg fried rice with sliced pork",
            category = "Rice",
            rating = 3.9,
            time = "30 min",
            chef = "Chef Lee",
            image = "",
            ingredients = emptyList()
        ),
        Recipe(
            id = 5,
            name = "Vegan Stir-fry with Tofu",
            category = "Vegan",
            rating = 4.2,
            time = "25 min",
            chef = "Chef Sophia",
            image = "",
            ingredients = emptyList()
        )
    )

    override suspend fun getRecipes(): List<Recipe> {
        return fakeRecipes
    }

    override suspend fun getRecipe(id: Int): Recipe {
        return fakeRecipes.first { it.id == id }
    }
}
