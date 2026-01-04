package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.BookmarkDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await

class BookmarkRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : BookmarkRepository {

    private fun FirebaseAuth.authStateFlow(): Flow<FirebaseUser?> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser)
        }
        addAuthStateListener(authStateListener)
        awaitClose { removeAuthStateListener(authStateListener) }
    }

    override val bookmarks: Flow<Set<Long>> = auth.authStateFlow().flatMapLatest { user ->
        if (user == null) {
            flowOf(emptySet())
        } else {
            callbackFlow {
                val listenerRegistration = firestore.collection("users")
                    .document(user.uid)
                    .collection("user_bookmarks")
                    .addSnapshotListener { snapshot, error ->
                        if (error != null) {
                            close(error)
                            return@addSnapshotListener
                        }
                        if (snapshot != null) {
                            val list = snapshot.toObjects(BookmarkDto::class.java)
                            trySend(list.map { it.recipeId }.toSet())
                        }
                    }
                awaitClose { listenerRegistration.remove() }
            }
        }
    }

    override suspend fun getBookmarkedRecipeIds(): Set<Long> {
        return try {
            bookmarks.first()
        } catch (e: Exception) {
            emptySet()
        }
    }

    override suspend fun addBookmark(recipeId: Long) {
        val user = auth.currentUser ?: return
        val bookmark = BookmarkDto(userId = user.uid, recipeId = recipeId)
        firestore.collection("users")
            .document(user.uid)
            .collection("user_bookmarks")
            .document(recipeId.toString())
            .set(bookmark)
            .await()
    }

    override suspend fun removeBookmark(recipeId: Long) {
        val user = auth.currentUser ?: return
        firestore.collection("users")
            .document(user.uid)
            .collection("user_bookmarks")
            .document(recipeId.toString())
            .delete()
            .await()
    }
}
