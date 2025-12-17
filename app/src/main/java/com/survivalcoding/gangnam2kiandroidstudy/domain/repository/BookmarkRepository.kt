package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

interface BookmarkRepository {
    suspend fun unBookmarkRecipe(id: Int): Boolean
}