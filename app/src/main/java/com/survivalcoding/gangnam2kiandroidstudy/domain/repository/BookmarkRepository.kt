package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun updateBookmarkRecipe(id: Int): Flow<Result<Unit, String>>
    fun getBookmarks(): Flow<Result<List<Int>, String>>
}
