package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.BookmarkDto
import com.survivalcoding.gangnam2kiandroidstudy.data.local.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.local.BookmarkEntity
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class CachedBookmarkRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val bookmarkDao: BookmarkDao
) : BookmarkRepository {

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        // Firestore SnapshotListener를 통해 실시간 동기화 시작
        observeRemoteBookmarks()
    }

    private fun observeRemoteBookmarks() {
        auth.authStateFlow().onEach { user ->
            if (user != null) {
                firestore.collection("users")
                    .document(user.uid)
                    .collection("user_bookmarks")
                    .addSnapshotListener { snapshot, error ->
                        if (error == null && snapshot != null) {
                            val remoteIds = snapshot.toObjects(BookmarkDto::class.java).map { it.recipeId }
                            scope.launch {
                                // 1. 로컬 DB 초기화 (단순화: 전체 교체)
                                // 실제 서비스에서는 Diff를 계산하여 효율적으로 업데이트 권장
                                bookmarkDao.getBookmarkedRecipeIds().forEach { localId ->
                                    if (localId !in remoteIds) bookmarkDao.deleteBookmark(localId)
                                }
                                remoteIds.forEach { remoteId ->
                                    bookmarkDao.insertBookmark(BookmarkEntity(recipeId = remoteId))
                                }
                            }
                        }
                    }
            } else {
                // 로그아웃 시 로컬 북마크 초기화? (선택 사항)
            }
        }.launchIn(scope)
    }

    private fun FirebaseAuth.authStateFlow(): Flow<FirebaseUser?> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser)
        }
        addAuthStateListener(authStateListener)
        awaitClose { removeAuthStateListener(authStateListener) }
    }

    override val bookmarks: Flow<Set<Long>> = bookmarkDao.getBookmarkedRecipeIdsFlow()
        .map { it.toSet() }
        .distinctUntilChanged()

    override suspend fun getBookmarkedRecipeIds(): Set<Long> {
        return bookmarkDao.getBookmarkedRecipeIds().toSet()
    }

    override suspend fun addBookmark(recipeId: Long) {
        val user = auth.currentUser ?: return
        // 1. Firestore 업데이트
        firestore.collection("users")
            .document(user.uid)
            .collection("user_bookmarks")
            .document(recipeId.toString())
            .set(BookmarkDto(userId = user.uid, recipeId = recipeId))
            .await()
        
        // 2. 로컬 DB는 SnapshotListener에 의해 동기화됨 (필요 시 즉시 업데이트 가능)
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
