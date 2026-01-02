package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository

class AddBookmarkUseCase(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend fun execute(recipeId: Int) {
        bookmarkRepository.addBookmark(recipeId)
    }
}
