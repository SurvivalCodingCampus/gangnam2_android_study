package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import javax.inject.Inject

class ToggleBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend fun execute(recipeId: Int): Result<Boolean, String> {
        return try {
            val isBookmarked = bookmarkRepository.isBookmarked(recipeId)

            if (isBookmarked) {
                bookmarkRepository.removeBookmark(recipeId)
                Result.Success(false)
            } else {
                bookmarkRepository.addBookmark(recipeId)
                Result.Success(true)
            }
        } catch (e: Exception) {
            Result.Error("북마크 토글 실패")
        }
    }

}