package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSavedRecipesUseCase(
    private val bookmarkRepository: BookmarkRepository,
    private val recipeRepository: RecipeRepository,
) {
    operator fun invoke(): Flow<List<Recipe>> {
        return bookmarkRepository.bookmarks.map { saved ->
            val all = when (val result = recipeRepository.findRecipes()) {
                is Result.Success -> result.data
                is Result.Error -> emptyList()
            }
            all.filter { saved.contains(it.id) }
        }
    }
}