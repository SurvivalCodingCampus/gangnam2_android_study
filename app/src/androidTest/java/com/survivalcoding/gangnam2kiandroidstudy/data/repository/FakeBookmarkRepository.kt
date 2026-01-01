package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository

class FakeBookmarkRepository : BookmarkRepository {
    private val bookmarks = mutableSetOf<Long>()

    override suspend fun getBookmarkedRecipeIds(): Set<Long> {
        return bookmarks.toSet()
    }

    override suspend fun addBookmark(recipeId: Long) {
        bookmarks.add(recipeId)
    }

    override suspend fun removeBookmark(recipeId: Long) {
        bookmarks.remove(recipeId)
    }
}
