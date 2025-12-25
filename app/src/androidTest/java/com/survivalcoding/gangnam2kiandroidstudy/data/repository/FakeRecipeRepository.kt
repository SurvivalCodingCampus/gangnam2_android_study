package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.HomeImage
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import javax.inject.Inject

class FakeRecipeRepository @Inject constructor() : RecipeRepository {

    private var shouldThrowError = false

    fun setShouldThrowError(value: Boolean) {
        shouldThrowError = value
    }

    private val fakeRecipes = listOf(
        Recipe(
            id = 1,
            category = "Cereal",
            title = "Traditional spare ribs baked",
            imageUrls = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            chefId = 1,
            chefName = "Chef John",
            time = "20 min",
            rating = 4.0,
            createdAt = 1736034600000,
            homeImage = HomeImage.FOOD1,
        ),
        Recipe(
            id = 2,
            category = "Lunch",
            title = "Spice roasted chicken with flavored rice",
            imageUrls = "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
            chefId = 2,
            chefName = "Mark Kelvin",
            time = "20 min",
            rating = 4.0,
            createdAt = 1739332200000,
            homeImage = HomeImage.FOOD2,
        ),
        Recipe(
            id = 3,
            category = "Chinese",
            title = "Spicy fried rice mix chicken bali",
            imageUrls = "https://cdn.pixabay.com/photo/2019/09/07/19/02/spanish-paella-4459519_1280.jpg",
            chefId = 3,
            chefName = "Spicy Nelly",
            time = "20 min",
            rating = 4.0,
            createdAt = 1742453100000,
            homeImage = HomeImage.FOOD3,
        )
    )

    override suspend fun getRecipes(): List<Recipe> {
        if (shouldThrowError) {
            throw Exception("Network Error")
        }
        return fakeRecipes
    }

    override suspend fun getRecipeById(id: Int): Recipe? {
        return fakeRecipes.find { it.id == id }
    }

    override suspend fun getRecipesByIds(ids: List<Int>): List<Recipe> {
        return fakeRecipes.filter { it.id in ids }
    }
}
