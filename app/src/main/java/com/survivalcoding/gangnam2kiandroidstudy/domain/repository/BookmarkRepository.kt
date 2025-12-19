package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

interface BookmarkRepository {
    suspend fun getBookmarkId(): List<Int>
    suspend fun removeBookmarkId(id: Int): List<Int>
    suspend fun addBookmarkId(id: Int): List<Int>
}