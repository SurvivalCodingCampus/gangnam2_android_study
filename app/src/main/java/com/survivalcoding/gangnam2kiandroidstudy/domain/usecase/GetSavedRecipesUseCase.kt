@file:OptIn(ExperimentalCoroutinesApi::class)

package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.util.NetworkErrorHandler
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

class GetSavedRecipesUseCase(
    private val recipeRepository: RecipeRepository,
    private val bookmarkRepository: BookmarkRepository,
) {
    operator fun invoke(): Flow<AppResult<List<Recipe>, NetworkError>> {
        val profileId = 1L
        return bookmarkRepository.getBookmarks(profileId).mapLatest { bookmarks ->
            NetworkErrorHandler.handle {
                val bookmarkSet = bookmarks.toSet()

                when (val result = recipeRepository.getSavedRecipes()) {
                    is AppResult.Success -> {
                        AppResult.Success(
                            result.data
                                .map { it.copy(isSaved = it.id in bookmarkSet) }
                                .filter { it.isSaved },
                        )
                    }
                    is AppResult.Error -> result
                }
            }
        }
    }
}