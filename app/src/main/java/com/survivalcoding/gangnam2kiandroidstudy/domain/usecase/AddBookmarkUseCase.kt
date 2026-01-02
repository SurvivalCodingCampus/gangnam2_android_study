package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository

class AddBookmarkUseCase(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(recipeId: Long) {
        bookmarkRepository.addBookmark(recipeId)
    }
}
