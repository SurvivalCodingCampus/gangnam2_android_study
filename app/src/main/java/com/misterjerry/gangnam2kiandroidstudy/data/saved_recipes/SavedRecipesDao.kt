package com.misterjerry.gangnam2kiandroidstudy.data.saved_recipes

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Query
import com.misterjerry.gangnam2kiandroidstudy.domain.model.SavedRecipesEntity

@Dao
interface SavedRecipesDao {
    
    @Query("SELECT * FROM SavedRecipesEntity")
    suspend fun getSavedRecipesList(): List<SavedRecipesEntity>

    @Query("DELETE FROM SavedRecipesEntity WHERE id = :id")
    suspend fun deleteSavedRecipe(id: Int)

    @Query("INSERT OR REPLACE INTO SavedRecipesEntity (id) VALUES (:id)")
    suspend fun addSavedRecipe(id: Int)


    //Content Provider를 위한 데이터 조회 (전체 목록)
    @Query("SELECT * FROM SavedRecipesEntity")
    fun selectAllForProvider(): Cursor


    //Content Provider를 위한 데이터 조회 (단일 항목)
    @Query("SELECT * FROM SavedRecipesEntity WHERE id = :id")
    fun selectByIdForProvider(id: Int): Cursor

}
