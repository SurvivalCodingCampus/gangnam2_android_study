package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.util.suspendRunCatching
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class GetFilteredRecipesUseCase(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(category: String): Result<List<Recipe>> {
        return suspendRunCatching {
            recipeRepository.getFilteredRecipesByCategory(category)
        }
    }
}