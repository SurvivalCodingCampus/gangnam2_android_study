package com.survivalcoding.gangnam2kiandroidstudy.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.survivalcoding.gangnam2kiandroidstudy.data.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM bookmark WHERE profile_id = :profileId")
    fun findAllByProfileId(profileId: Long): Flow<List<BookmarkEntity>>

    @Query("SELECT * FROM bookmark WHERE profile_id = :profileId")
    fun findAllByProfileIdSync(profileId: Long): List<BookmarkEntity>

    @Query("SELECT * FROM bookmark WHERE recipe_id = :recipeId")
    suspend fun findByRecipeId(recipeId: Long): BookmarkEntity?

    @Insert
    suspend fun insertBookmark(bookmark: BookmarkEntity)

    @Delete
    suspend fun deleteBookmark(bookmark: BookmarkEntity)
}