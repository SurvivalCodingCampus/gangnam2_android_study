package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlin.coroutines.cancellation.CancellationException

class GetBookmarkedRecipeIdsUseCase(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend fun execute(): Result<Set<Long>, String> {
        return try {
            val ids = bookmarkRepository.getBookmarkedRecipeIds()
            Result.Success(ids)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.Error(e.message ?: "Failed to load bookmarks")
        }
    }
}
