package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    val bookmarks: Flow<Set<Long>>

    suspend fun getBookmarkedRecipeIds(): Set<Long>
    suspend fun addBookmark(recipeId: Long)
    suspend fun removeBookmark(recipeId: Long)
}