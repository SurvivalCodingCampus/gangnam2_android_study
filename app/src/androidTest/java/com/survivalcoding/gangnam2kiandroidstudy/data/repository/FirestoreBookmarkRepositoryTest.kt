package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class FirestoreBookmarkRepositoryTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: BookmarkRepository

    @Inject
    lateinit var auth: FirebaseAuth

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testAddAndGetBookmark() = runTest {
        // 1. 로그인 (에뮬레이터 - staging 모듈이 이미 연결 설정됨)
        auth.signInAnonymously().await()

        // 2. 데이터 준비
        val recipe = Recipe(
            id = 999,
            category = "Test",
            name = "Test Recipe",
            image = "",
            chef = "Tester",
            time = "10min",
            rating = 5.0,
            ingredients = emptyList()
        )

        // 3. 추가
        repository.addBookmark(recipe)

        // 4. 검증 (List)
        val bookmarks = repository.getBookmarks()
        assertTrue("Bookmark list should contain the added recipe", bookmarks.any { it.id == 999 })

        // 5. 검증 (isBookmarked)
        val isBookmarked = repository.isBookmarked(999)
        assertTrue("isBookmarked should return true", isBookmarked)

        // 6. 삭제 및 검증
        repository.removeBookmark(recipe)
        val afterDelete = repository.getBookmarks()
        assertTrue(
            "Bookmark list should NOT contain the removed recipe",
            afterDelete.none { it.id == 999 })
    }

    @Test
    fun testSecurityRuleDeniesAccess() = runTest {
        // 1. User A로 로그인 및 데이터 저장
        auth.signInAnonymously().await()
        val userA_Uid = auth.currentUser!!.uid
        val recipe = Recipe(
            id = 777,
            category = "Secret",
            name = "Secret Recipe",
            image = "",
            chef = "Me",
            time = "0",
            rating = 0.0,
            ingredients = emptyList()
        )
        repository.addBookmark(recipe)

        // 2. 로그아웃 (인증 정보 제거)
        auth.signOut()

        // 3. User A의 데이터에 접근 시도 (권한 거부 예상)
        val collection = Firebase.firestore
            .collection("users")
            .document(userA_Uid) // User A의 문서
            .collection("bookmarks")

        try {
            collection.get().await()
            // 여기까지 오면 실패 (보안 규칙이 뚫림)
            throw java.lang.AssertionError("Security Rule Failed: Unauthorized access was allowed!")
        } catch (e: Exception) {
            // 예외가 발생해야 성공 (PERMISSION_DENIED 등)
            println("Security Rule Passed: Access denied as expected. Error: ${e.message}")
            assertTrue(
                "Should fail with PERMISSION_DENIED",
                e.message?.contains("PERMISSION_DENIED") == true || e.message?.contains("Missing or insufficient permissions") == true
            )
        }
    }
}