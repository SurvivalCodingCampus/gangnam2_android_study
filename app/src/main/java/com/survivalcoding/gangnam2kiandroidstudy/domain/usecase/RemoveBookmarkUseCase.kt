package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.util.suspendRunCatching
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository

class RemoveBookmarkUseCase(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(id: Int): Result<Boolean> {
        return suspendRunCatching {
            bookmarkRepository.removeBookmark(id)
        }
    }
}