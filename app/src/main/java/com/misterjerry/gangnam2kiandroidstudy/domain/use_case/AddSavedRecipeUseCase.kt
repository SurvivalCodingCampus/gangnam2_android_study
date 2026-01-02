package com.misterjerry.gangnam2kiandroidstudy.domain.use_case

import com.misterjerry.gangnam2kiandroidstudy.domain.repository.SavedRecipesRepository

class AddSavedRecipeUseCase(
    private val repository: SavedRecipesRepository
) {
    suspend fun execute(id: Int) {
        repository.addSavedRecipe(id)
    }
}