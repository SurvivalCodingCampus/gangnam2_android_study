package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.util.NetworkErrorHandler
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository

class ToggleBookmarkUseCase(
    private val bookmarkRepository: BookmarkRepository,
) {
    suspend operator fun invoke(recipeId: Long): AppResult<Unit, NetworkError> {
        return NetworkErrorHandler.handle {
            bookmarkRepository.toggleBookmark(recipeId)
            AppResult.Success(Unit)
        }
    }
}