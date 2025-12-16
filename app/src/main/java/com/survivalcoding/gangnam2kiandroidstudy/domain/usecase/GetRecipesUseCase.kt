package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class GetRecipesUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(): Result<List<Recipe>, NetworkError> {
        return try {
            Result.Success(repository.getRecipes())
        } catch (e: NetworkError) {
            Result.Failure(e)
        }
    }
}
