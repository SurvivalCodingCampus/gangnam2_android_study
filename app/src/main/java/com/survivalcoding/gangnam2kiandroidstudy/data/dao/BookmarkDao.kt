package com.survivalcoding.gangnam2kiandroidstudy.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.survivalcoding.gangnam2kiandroidstudy.data.entity.BookmarkEntity

@Dao
interface BookmarkDao {
    @Query("SELECT recipeId FROM bookmarks")
    suspend fun getAllIds(): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE recipeId = :recipeId")
    suspend fun delete(recipeId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE recipeId = :recipeId)")
    suspend fun isBookmarked(recipeId: Int): Boolean
}