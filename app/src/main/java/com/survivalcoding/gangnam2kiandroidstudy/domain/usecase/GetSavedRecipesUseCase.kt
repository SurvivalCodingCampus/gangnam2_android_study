package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.util.suspendRunCatching
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class GetSavedRecipesUseCase(
    private val recipeRepository: RecipeRepository,
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(): Result<List<Recipe>> {
        return suspendRunCatching {
            val bookmarkIds = bookmarkRepository.getBookmarkedRecipeIds()
            val allRecipes = recipeRepository.getAllRecipes()
            allRecipes.filter { it.id in bookmarkIds }
        }
    }
}