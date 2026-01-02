package com.survivalcoding.gangnam2kiandroidstudy.data.bookmark.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class InMemoryBookmarkRepositoryImpl : BookmarkRepository {

    private val bookmarkedRecipeIds = MutableStateFlow<Set<Int>>(emptySet())

    override fun getBookmarkedRecipeIds(): Flow<List<Int>> {
        return bookmarkedRecipeIds.asStateFlow().map { it.toList() }
    }

    override suspend fun addBookmark(id: Int) {
        bookmarkedRecipeIds.value = bookmarkedRecipeIds.value + id
    }

    override suspend fun removeSavedRecipeId(id: Int) {
        bookmarkedRecipeIds.value = bookmarkedRecipeIds.value - id
    }
}
