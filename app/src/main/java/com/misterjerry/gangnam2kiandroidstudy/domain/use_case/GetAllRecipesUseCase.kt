package com.misterjerry.gangnam2kiandroidstudy.domain.use_case

import com.misterjerry.gangnam2kiandroidstudy.domain.model.Recipe
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.RecipesRepository

class GetAllRecipesUseCase(
    val repository: RecipesRepository
) {

    suspend fun execute(): List<Recipe> {
        return repository.getAllRecipes()
    }
}