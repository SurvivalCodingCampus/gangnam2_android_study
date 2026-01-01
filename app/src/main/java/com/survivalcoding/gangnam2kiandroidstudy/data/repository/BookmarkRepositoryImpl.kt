package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.dao.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.entity.BookmarkEntity
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao
) : BookmarkRepository {

    override suspend fun getSavedRecipeIds(): List<Int> {
        return bookmarkDao.getAllIds()
    }

    override suspend fun addBookmark(recipeId: Int) {
        bookmarkDao.insert(BookmarkEntity(recipeId))
    }

    override suspend fun removeBookmark(recipeId: Int) {
        bookmarkDao.delete(recipeId)
    }

    override suspend fun isBookmarked(recipeId: Int): Boolean {
        return bookmarkDao.isBookmarked(recipeId)
    }
}
