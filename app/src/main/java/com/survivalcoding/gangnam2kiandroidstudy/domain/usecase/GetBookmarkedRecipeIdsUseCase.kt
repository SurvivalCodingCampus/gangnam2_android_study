package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlin.coroutines.cancellation.CancellationException

import kotlinx.coroutines.flow.Flow

class GetBookmarkedRecipeIdsUseCase(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(): Flow<Set<Long>> {
        return bookmarkRepository.bookmarks
    }
}
