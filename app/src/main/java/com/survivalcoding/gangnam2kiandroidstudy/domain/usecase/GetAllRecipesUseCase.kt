package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.util.suspendRunCatching
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class GetAllRecipesUseCase(private val provideRecipeRepository: RecipeRepository) {
    suspend operator fun invoke(): Result<List<Recipe>> {
        return suspendRunCatching {
            provideRecipeRepository.getAllRecipes()
        }
    }
}