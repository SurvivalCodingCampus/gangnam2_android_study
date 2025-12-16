package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository

class FilterRecipesUseCase(
    private val recipeRepository: RecipeRepository,
) {

    suspend fun execute(query: String): Result<List<Recipe>, String> {
        if (query.isEmpty()) {
            return Result.Failure("Query is empty")
        }

        return try {

            Result.Success(recipeRepository.getRecipes().filter { recipe ->
                recipe.title.lowercase().contains(query.lowercase())
            })
        } catch (e: Exception) {
            Result.Failure(e.message ?: "Unknown error")
        }

    }

//    suspend operator fun invoke(query: String): List<Recipe> {
//        return recipeRepository.getRecipes().filter { recipe ->
//            recipe.title.lowercase().contains(query.lowercase())
//        }
//    }
}