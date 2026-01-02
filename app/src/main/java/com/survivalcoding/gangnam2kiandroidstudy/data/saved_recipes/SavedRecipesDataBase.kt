package com.survivalcoding.gangnam2kiandroidstudy.data.saved_recipes

import androidx.room.Database
import androidx.room.RoomDatabase
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.SavedRecipesEntity

@Database(entities = [SavedRecipesEntity::class], version = 1)
abstract class SavedRecipesDatabase : RoomDatabase() {
    abstract fun savedRecipesDao(): SavedRecipesDao
}