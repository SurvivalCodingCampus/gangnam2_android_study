package com.survivalcoding.gangnam2kiandroidstudy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.survivalcoding.gangnam2kiandroidstudy.data.dao.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.dao.RecipeDao
import com.survivalcoding.gangnam2kiandroidstudy.data.entity.BookmarkEntity
import com.survivalcoding.gangnam2kiandroidstudy.data.entity.RecipeEntity

@Database(
    entities = [
        BookmarkEntity::class,
        RecipeEntity::class,
    ],
    version = 2,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun recipeDao(): RecipeDao
}
