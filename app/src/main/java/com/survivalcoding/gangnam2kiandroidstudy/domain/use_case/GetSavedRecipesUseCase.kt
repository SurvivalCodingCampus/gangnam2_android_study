package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.SavedRecipesRepository

class GetSavedRecipesUseCase(
    private val repository: SavedRecipesRepository
) {
    suspend fun execute(): List<Recipe> {
        return repository.getSavedRecipes()
    }
}