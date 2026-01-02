package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    // 북마크된 레시피의 ID 목록
    fun getSavedRecipeIds(): Flow<List<Int>>

    // 북마크 추가
    suspend fun addBookmark(recipeId: Int)

    // 북마크 제거
    suspend fun removeBookmark(recipeId: Int)

    // 토글
    suspend fun isBookmarked(recipeId: Int): Boolean
}