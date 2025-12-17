package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

interface BookmarkRepository {
    suspend fun getBookmarkedRecipeIds(): Set<Long>
    suspend fun addBookmark(recipeId: Long)
    suspend fun removeBookmark(recipeId: Long)
}