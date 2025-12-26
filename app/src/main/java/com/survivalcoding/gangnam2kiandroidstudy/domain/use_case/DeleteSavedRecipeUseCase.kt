package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipesRepository

class DeleteSavedRecipeUseCase(
    private val repository: RecipesRepository
) {
    suspend fun execute(id: Int) {
        repository.deleteSavedRecipe(id)
    }


}