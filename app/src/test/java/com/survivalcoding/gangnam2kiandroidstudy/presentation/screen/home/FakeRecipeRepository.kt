package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class FakeRecipeRepository(
    private val recipes: List<Recipe>
) : RecipeRepository {

    override suspend fun getRecipes(): List<Recipe> {
        return recipes
    }
}