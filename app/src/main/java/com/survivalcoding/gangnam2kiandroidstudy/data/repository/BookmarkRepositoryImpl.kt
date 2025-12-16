package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.BookmarkDataSource

class BookmarkRepositoryImpl private constructor(
    private val bookmarkDataSource: BookmarkDataSource
) : BookmarkRepository {
    companion object {
        @Volatile private var instance: BookmarkRepository? = null

        fun getInstance(bookmarkDataSource: BookmarkDataSource) =
            instance ?: synchronized(this) {
                instance ?: BookmarkRepositoryImpl(bookmarkDataSource).also { instance = it }
            }
    }
}