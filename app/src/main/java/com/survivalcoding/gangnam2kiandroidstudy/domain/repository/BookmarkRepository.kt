package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    suspend fun toggleBookmark(recipeId: Long)
    fun getBookmarks(profileId: Long): Flow<List<Long>>
}