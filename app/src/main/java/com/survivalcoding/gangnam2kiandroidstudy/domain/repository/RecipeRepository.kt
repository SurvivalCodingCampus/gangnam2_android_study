package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

interface RecipeRepository {
    suspend fun getAllRecipes(): List<Recipe>

    suspend fun getFilteredRecipes(keyword: String): List<Recipe>

    suspend fun getFilteredRecipesByCategory(category: String): List<Recipe>

    suspend fun getRecipeById(id: Int): Recipe?
}