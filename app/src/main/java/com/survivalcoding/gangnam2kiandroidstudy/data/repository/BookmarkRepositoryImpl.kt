package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.UserDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toUser
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository

class BookmarkRepositoryImpl(
    private val userDataSource: UserDataSource,
) : BookmarkRepository {

    private val bookmarkList = mutableListOf<Int>()

    override suspend fun getBookmarkId(): List<Int> {
        return userDataSource.getUser().toUser().savedRecipesId
    }

    override suspend fun removeBookmarkId(id: Int): List<Int> {
        if (id != 0) {
            bookmarkList.add(id)
            return getBookmarkId().filter { it !in bookmarkList }
        } else {
            return getBookmarkId()
        }
    }


}