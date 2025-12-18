package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor() : BookmarkRepository {
    // 첫 로드 시 10개 띄우기 위함
    private val bookmarkedIds = mutableSetOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    )

    override suspend fun getSavedRecipeIds(): List<Int> {
        return bookmarkedIds.toList()
    }

    override suspend fun addBookmark(recipeId: Int) {
        bookmarkedIds.add(recipeId)
    }

    override suspend fun removeBookmark(recipeId: Int) {
        bookmarkedIds.remove(recipeId)
    }

    override suspend fun isBookmarked(recipeId: Int): Boolean {
        return bookmarkedIds.contains(recipeId)
    }
}
