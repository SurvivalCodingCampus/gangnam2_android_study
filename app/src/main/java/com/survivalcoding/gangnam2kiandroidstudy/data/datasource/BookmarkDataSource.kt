package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

interface BookmarkDataSource {
    suspend fun getBookmarkedRecipeIds(): List<Int>

    suspend fun removeBookmark(recipeId: Int): Boolean
}