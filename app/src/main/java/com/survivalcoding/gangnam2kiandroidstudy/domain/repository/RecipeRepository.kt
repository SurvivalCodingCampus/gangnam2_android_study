package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import android.database.Cursor
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

interface RecipeRepository {
    suspend fun findAll(): Result<List<Recipe>, NetworkError>
    suspend fun search(query: String, time: String, rate: Double, category: String): Result<List<Recipe>, NetworkError>

    suspend fun getRecipes(): List<Recipe>
    suspend fun getRecipeById(recipeId: Int): Recipe?

    fun getRecipesCursor(): Cursor
    fun getRecipeCursorById(recipeId: Int): Cursor
}
