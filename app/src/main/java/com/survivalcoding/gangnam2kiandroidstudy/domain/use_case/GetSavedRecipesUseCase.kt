package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.data.saved_recipes.SavedRecipesDao
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.SavedRecipesEntity

class GetSavedRecipesUseCase(
    private val dao: SavedRecipesDao
) {
    suspend fun execute(): List<SavedRecipesEntity> {
        return dao.getSavedRecipesList()
    }
}