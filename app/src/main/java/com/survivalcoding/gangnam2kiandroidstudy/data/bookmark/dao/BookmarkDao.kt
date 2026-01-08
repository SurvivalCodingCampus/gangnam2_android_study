package com.survivalcoding.gangnam2kiandroidstudy.data.bookmark.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.survivalcoding.gangnam2kiandroidstudy.data.bookmark.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(vararg bookmarkEntity: BookmarkEntity)

    @Query("DELETE FROM bookmark WHERE recipeId = :recipeId")
    suspend fun removeBookmark(recipeId: Int)

    @Query("SELECT recipeId FROM bookmark")
    fun getAllBookmarkedRecipeIds(): Flow<List<Int>>

    // ContentProvider에서 사용할 동기 조회 (recipeId 목록만 제공)
    @Query("SELECT recipeId FROM bookmark")
    fun getAllBookmarkedRecipeIdsOnce(): List<Int>
}
