package com.misterjerry.gangnam2kiandroidstudy.domain.repository

import com.misterjerry.gangnam2kiandroidstudy.domain.model.SavedRecipesEntity

interface SavedRecipesRepository {
    suspend fun getSavedRecipes(): List<SavedRecipesEntity>

    suspend fun deleteSavedRecipe(id: Int): Result<Unit>

    suspend fun addSavedRecipe(id: Int): Result<Unit>
}