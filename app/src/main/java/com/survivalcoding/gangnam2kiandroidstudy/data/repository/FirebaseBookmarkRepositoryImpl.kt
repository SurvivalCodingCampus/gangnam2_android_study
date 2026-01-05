package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseBookmarkRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : BookmarkRepository {

    private val currentUser get() = auth.currentUser

    override fun getSavedRecipeIds(): Flow<List<Int>> = callbackFlow {
        val user = currentUser
        if (user == null) {
            trySend(emptyList())
            close()
            return@callbackFlow
        }

        val collectionRef = firestore.collection("users")
            .document(user.uid)
            .collection("bookmarks")

        val listener = collectionRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val ids = snapshot.documents.mapNotNull { doc ->
                    doc.id.toIntOrNull()
                }
                trySend(ids)
            }
        }

        awaitClose { listener.remove() }
    }

    override suspend fun addBookmark(recipeId: Int) {
        val user = currentUser ?: return
        val bookmarkData = mapOf(
            "savedAt" to com.google.firebase.Timestamp.now()
        )
        
        firestore.collection("users")
            .document(user.uid)
            .collection("bookmarks")
            .document(recipeId.toString())
            .set(bookmarkData)
            .await()
    }

    override suspend fun removeBookmark(recipeId: Int) {
        val user = currentUser ?: return
        
        firestore.collection("users")
            .document(user.uid)
            .collection("bookmarks")
            .document(recipeId.toString())
            .delete()
            .await()
    }

    override suspend fun isBookmarked(recipeId: Int): Boolean {
        val user = currentUser ?: return false
        
        val doc = firestore.collection("users")
            .document(user.uid)
            .collection("bookmarks")
            .document(recipeId.toString())
            .get()
            .await()
            
        return doc.exists()
    }
}
