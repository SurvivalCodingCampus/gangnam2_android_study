package com.misterjerry.gangnam2kiandroidstudy.domain.use_case

import com.misterjerry.gangnam2kiandroidstudy.domain.model.SavedRecipesEntity
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.SavedRecipesRepository

class GetSavedRecipesUseCase(
    private val repository: SavedRecipesRepository
) {
    suspend fun execute(): List<SavedRecipesEntity> {
        return repository.getSavedRecipes()
    }
}