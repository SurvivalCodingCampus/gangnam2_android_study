package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun getBookmarkedRecipeIds(): Flow<List<Int>>
    suspend fun addBookmark(id: Int)
    suspend fun removeSavedRecipeId(id:Int)
}
