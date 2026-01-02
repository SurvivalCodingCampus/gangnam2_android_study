package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object MockBookmarkRepositoryImpl : BookmarkRepository {

    private val bookmarks = MockRecipeRepositoryImpl.mockRecipes
        .map { it.copy(isSaved = true) }
        .toMutableList()
    private val _bookmarksFlow = MutableStateFlow(bookmarks.map { it.id })

    override suspend fun toggleBookmark(recipeId: Long) {
        val existing = bookmarks.find { it.id == recipeId }

        if (existing != null) {
            bookmarks.remove(existing)
        } else {
            val recipe = MockRecipeRepositoryImpl.mockRecipes.find { it.id == recipeId }
            recipe?.let { bookmarks.add(it.copy(isSaved = true)) }
        }

        _bookmarksFlow.value = bookmarks.map { it.id }
    }

    override fun getBookmarks(profileId: Long): Flow<List<Long>> {
        return _bookmarksFlow.asStateFlow()
    }
}