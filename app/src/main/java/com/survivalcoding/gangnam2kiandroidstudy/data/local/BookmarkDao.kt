package com.survivalcoding.gangnam2kiandroidstudy.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookmarkDao {
    @Query("SELECT recipeId FROM bookmarks")
    suspend fun getBookmarkedRecipeIds(): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE recipeId = :recipeId")
    suspend fun deleteBookmark(recipeId: Long)
}
