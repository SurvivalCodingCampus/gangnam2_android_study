package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository

class RemoveBookmarkUseCase(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(recipeId: Long) {
        bookmarkRepository.removeBookmark(recipeId)
    }
}
