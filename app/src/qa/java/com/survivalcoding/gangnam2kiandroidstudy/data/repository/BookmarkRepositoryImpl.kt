package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.local.dao.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.local.entity.BookmarkEntity
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository

class BookmarkRepositoryImpl(
    private val bookmarkDao: BookmarkDao
) : BookmarkRepository {
    override suspend fun getBookmarkId(): List<Int> {
        return bookmarkDao.getBookmarkIds()
    }

    override suspend fun removeBookmarkId(id: Int): List<Int> {
        bookmarkDao.delete(id)
        return getBookmarkId()
    }

    override suspend fun addBookmarkId(id: Int): List<Int> {
        bookmarkDao.insert(BookmarkEntity(id))
        return getBookmarkId()
    }
}
