package com.misterjerry.gangnam2kiandroidstudy.domain.use_case

import com.misterjerry.gangnam2kiandroidstudy.data.saved_recipes.SavedRecipesDao
import com.misterjerry.gangnam2kiandroidstudy.domain.model.SavedRecipesEntity

class GetSavedRecipesUseCase(
    private val dao: SavedRecipesDao
) {
    suspend fun execute(): List<SavedRecipesEntity> {
        return dao.getSavedRecipesList()
    }
}