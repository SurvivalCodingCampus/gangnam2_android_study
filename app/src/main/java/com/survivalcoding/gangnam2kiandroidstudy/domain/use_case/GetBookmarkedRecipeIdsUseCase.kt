package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow

class GetBookmarkedRecipeIdsUseCase(
    private val bookmarkRepository: BookmarkRepository
) {
    fun execute(): Flow<List<Int>> {
        return bookmarkRepository.getBookmarkedRecipeIds()
    }
}
