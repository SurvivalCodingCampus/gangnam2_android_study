package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.data.Repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

class FakeRecipeRepository(
    private val recipes: List<Recipe>
) : RecipeRepository {

    override suspend fun getRecipes(): List<Recipe> {
        return recipes
    }
}