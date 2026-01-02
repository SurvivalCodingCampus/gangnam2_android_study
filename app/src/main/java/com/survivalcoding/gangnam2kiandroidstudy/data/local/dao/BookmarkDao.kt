package com.survivalcoding.gangnam2kiandroidstudy.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.survivalcoding.gangnam2kiandroidstudy.data.local.entity.BookmarkEntity

@Dao
interface BookmarkDao {

    @Query("SELECT recipeId FROM bookmark")
    suspend fun getBookmarkIds(): List<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: BookmarkEntity)

    @Query("DELETE FROM bookmark WHERE recipeId = :recipeId")
    suspend fun delete(recipeId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmark WHERE recipeId = :recipeId)")
    suspend fun isBookmarked(recipeId: Int): Boolean
}
