package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.survivalcoding.gangnam2kiandroidstudy.data.local.entity.BookmarkEntity
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.tasks.await

class BookmarkRepositoryImpl(
//    private val bookmarkDao: BookmarkDao,
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
): BookmarkRepository {

    private fun collection() =
        firestore.collection("users")
            .document(auth.currentUser!!.uid)
            .collection("bookmarks")

    override suspend fun getBookmarkId(): List<Int> {
        return collection()
            .get()
            .await()
            .documents
            .mapNotNull { it.id.toIntOrNull() }
    }

    override suspend fun addBookmarkId(id: Int): List<Int> {
        collection()
            .document(id.toString())
            .set(emptyMap<String, Any>())
            .await()

        return getBookmarkId()
    }

    override suspend fun removeBookmarkId(id: Int): List<Int> {
        collection()
            .document(id.toString())
            .delete()
            .await()

        return getBookmarkId()
    }

//    override suspend fun getBookmarkId(): List<Int> =
//        bookmarkDao.getBookmarkIds()
//
//    override suspend fun addBookmarkId(id: Int): List<Int> {
//        bookmarkDao.insert(BookmarkEntity(id))
//        return getBookmarkId()
//    }
//
//    override suspend fun removeBookmarkId(id: Int): List<Int> {
//        bookmarkDao.delete(id)
//        return getBookmarkId()
//    }

}