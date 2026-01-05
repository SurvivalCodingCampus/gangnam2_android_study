package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.survivalcoding.gangnam2kiandroidstudy.core.HttpException
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FirebaseBookmarkRepositoryImplTest {

    private val firestore = Firebase.firestore
    private val auth = Firebase.auth
    private lateinit var bookmarkRepository: BookmarkRepository

    @Before
    fun setUp() {
        bookmarkRepository = FirebaseBookmarkRepositoryImpl()
    }

    @After
    fun tearDown() = runTest {
        val user = auth.currentUser
        if (user != null) {
            val bookmarks = firestore.collection("users")
                .document(user.uid)
                .collection("bookmarks")
                .get()
                .await()

            for (doc in bookmarks.documents) {
                doc.reference.delete().await()
            }

            user.delete().await()
            auth.signOut()
        }
    }

    @Test
    fun toggleBookmark() = runTest {
        loginTestUser()
        val recipeId = 1L
        val userId = auth.currentUser!!.uid

        bookmarkRepository.toggleBookmark(recipeId)

        val doc = firestore.collection("users").document(userId)
            .collection("bookmarks").document(recipeId.toString()).get().await()
        assertTrue(doc.exists())
    }

    @Test
    fun getBookmarks() = runTest {
        loginTestUser()
        bookmarkRepository.toggleBookmark(1L)
        bookmarkRepository.toggleBookmark(2L)
        bookmarkRepository.toggleBookmark(3L)

        val result = bookmarkRepository.getBookmarks(1L).first()

        assertEquals(3, result.size)
    }

    @Test(expected = HttpException::class)
    fun toggleBookmarkException() = runTest {
        auth.signOut()
        bookmarkRepository.toggleBookmark(1L)
    }

    private suspend fun loginTestUser(email: String = "test@example.com") {
        try {
            auth.createUserWithEmailAndPassword(email, "password123").await()
        } catch (e: Exception) {
            auth.signInWithEmailAndPassword(email, "password123").await()
        }
    }

    companion object {
        init {
            Firebase.firestore.useEmulator("10.0.2.2", 9090)
            Firebase.auth.useEmulator("10.0.2.2", 9099)
        }
    }
}