package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

interface BookmarkRepository {
    suspend fun getBookmarkedRecipeIds(): List<Int>
    suspend fun removeBookmark(recipeId: Int): Boolean
}