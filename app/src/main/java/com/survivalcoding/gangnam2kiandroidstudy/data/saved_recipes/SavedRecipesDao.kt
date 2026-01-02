package com.survivalcoding.gangnam2kiandroidstudy.data.saved_recipes

import androidx.room.Dao
import androidx.room.Query
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.SavedRecipesEntity

@Dao
interface SavedRecipesDao {

    @Query("SELECT * FROM SavedRecipesEntity")
    suspend fun getSavedRecipesList(): List<SavedRecipesEntity>

    @Query("DELETE FROM SavedRecipesEntity WHERE id = :id")
    suspend fun deleteSavedRecipe(id: Int)

    @Query("INSERT OR REPLACE INTO SavedRecipesEntity (id) VALUES (:id)")
    suspend fun addSavedRecipe(id: Int)
}