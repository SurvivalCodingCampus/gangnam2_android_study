package com.survivalcoding.gangnam2kiandroidstudy

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.survivalcoding.gangnam2kiandroidstudy.core.di.databaseModule
import com.survivalcoding.gangnam2kiandroidstudy.data.local.AppDatabase
import com.survivalcoding.gangnam2kiandroidstudy.data.local.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.local.BookmarkEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class QaIntegrationTest : KoinTest {

    private val bookmarkDao: BookmarkDao by inject()
    private lateinit var memoryModule: Module
    private lateinit var db: AppDatabase

    @Before
    fun setup() {
        // In-Memory DB 생성
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()

        // 테스트용 모듈 정의
        memoryModule = module {
            single<AppDatabase> { db }
            single { get<AppDatabase>().bookmarkDao() }
        }

        // 기존 DatabaseModule을 언로드하고 테스트용 모듈 로드
        // 주의: 앱 시작 시 이미 로드된 모듈을 교체합니다.
        unloadKoinModules(databaseModule)
        loadKoinModules(memoryModule)
    }

    @After
    fun tearDown() {
        db.close()
        // 테스트용 모듈 언로드 및 기존 모듈 복구
        unloadKoinModules(memoryModule)
        loadKoinModules(databaseModule)
    }

    @Test
    fun testBookmarkDatabaseIntegration() = runBlocking {
        // Given
        val recipeId = 999L
        val bookmark = BookmarkEntity(
            recipeId = recipeId,
            timestamp = System.currentTimeMillis()
        )

        // When
        bookmarkDao.insertBookmark(bookmark)
        val savedIds = bookmarkDao.getBookmarkedRecipeIds()

        // Then
        assertEquals("저장된 북마크 개수가 1이어야 합니다.", 1, savedIds.size)
        assertEquals("저장된 레시피 ID가 일치해야 합니다.", recipeId, savedIds[0])
    }
}
