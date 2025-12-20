package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.BookmarkDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository

class BookmarkRepositoryImpl(
    private val bookmarkDataSource: BookmarkDataSource
) : BookmarkRepository {
    override suspend fun getBookmarkedRecipeIds(): List<Int> {
        return bookmarkDataSource.getBookmarkedRecipeIds()
    }

    override suspend fun removeBookmark(recipeId: Int): Boolean {
        return bookmarkDataSource.removeBookmark(recipeId)
    }
}