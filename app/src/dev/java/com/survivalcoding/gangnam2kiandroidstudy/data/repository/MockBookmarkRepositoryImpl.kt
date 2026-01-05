package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MockBookmarkRepositoryImpl : BookmarkRepository {
    private val _bookmarks = MutableStateFlow<Set<Long>>(emptySet())
    override val bookmarks: Flow<Set<Long>> = _bookmarks.asStateFlow()

    override suspend fun getBookmarkedRecipeIds(): Set<Long> {
        return _bookmarks.value
    }

    override suspend fun addBookmark(recipeId: Long) {
        _bookmarks.update { it + recipeId }
    }

    override suspend fun removeBookmark(recipeId: Long) {
        _bookmarks.update { it - recipeId }
    }
}
