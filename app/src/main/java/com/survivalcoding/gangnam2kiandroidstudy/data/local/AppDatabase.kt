package com.survivalcoding.gangnam2kiandroidstudy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [BookmarkEntity::class, RecipeEntity::class], version = 2)
@TypeConverters(RecipeTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun recipeDao(): RecipeDao
}
