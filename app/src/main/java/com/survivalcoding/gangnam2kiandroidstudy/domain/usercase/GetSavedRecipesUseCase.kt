package com.survivalcoding.gangnam2kiandroidstudy.domain.usercase

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class GetSavedRecipesUseCase(
    private val recipeRepository: RecipeRepository,
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(): List<Recipe> {
        val savedRecipes = recipeRepository.getRecipes()

        bookmarkRepository.getBookmarkedRecipeIds().collect { bookmarkedRecipeIds ->
            savedRecipes.forEach { recipe ->
                recipe.isBookmarked = bookmarkedRecipeIds.contains(recipe.id)
            }
        }

        return savedRecipes
    }
}
