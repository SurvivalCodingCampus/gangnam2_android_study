package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.util.Log
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository

class MockBookmarkRepositoryImpl : BookmarkRepository {

    override suspend fun toggleBookmark(recipeId: Long) {
        Log.d("MockBookmarkRepositoryImpl", "toggleBookmark: $recipeId")
    }
}