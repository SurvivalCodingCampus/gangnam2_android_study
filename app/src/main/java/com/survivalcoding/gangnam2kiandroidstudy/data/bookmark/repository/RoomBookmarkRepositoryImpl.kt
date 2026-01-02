package com.survivalcoding.gangnam2kiandroidstudy.data.bookmark.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.bookmark.dao.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.bookmark.entity.BookmarkEntity
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow

class RoomBookmarkRepositoryImpl(
    private val bookmarkDao: BookmarkDao
) : BookmarkRepository {
    override fun getBookmarkedRecipeIds(): Flow<List<Int>> {
        return bookmarkDao.getAllBookmarkedRecipeIds()
    }

    override suspend fun addBookmark(id: Int) {
        bookmarkDao.addBookmark(BookmarkEntity(id))
    }

    override suspend fun removeSavedRecipeId(id: Int) {
        bookmarkDao.removeBookmark(id)
    }
}
