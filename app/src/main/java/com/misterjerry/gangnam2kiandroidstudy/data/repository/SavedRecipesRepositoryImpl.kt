package com.misterjerry.gangnam2kiandroidstudy.data.repository

import com.misterjerry.gangnam2kiandroidstudy.data.saved_recipes.SavedRecipesDao
import com.misterjerry.gangnam2kiandroidstudy.domain.model.SavedRecipesEntity
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.SavedRecipesRepository

class SavedRecipesRepositoryImpl(
    private val dao: SavedRecipesDao
) : SavedRecipesRepository {
    override suspend fun getSavedRecipes(): List<SavedRecipesEntity> {
        return dao.getSavedRecipesList()
    }

    override suspend fun deleteSavedRecipe(id: Int) {
        dao.deleteSavedRecipe(id)
    }

    override suspend fun addSavedRecipe(id: Int) {
        dao.addSavedRecipe(id)
    }
}