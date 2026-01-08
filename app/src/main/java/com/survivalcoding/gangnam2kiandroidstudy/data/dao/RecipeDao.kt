package com.survivalcoding.gangnam2kiandroidstudy.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.survivalcoding.gangnam2kiandroidstudy.data.entity.RecipeEntity

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe WHERE id IN (:ids)")
    fun findByIdsSync(ids: List<Long>): List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipes: List<RecipeEntity>)

    @Query("DELETE FROM recipe")
    suspend fun deleteAll()
}