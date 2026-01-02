package com.survivalcoding.gangnam2kiandroidstudy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.survivalcoding.gangnam2kiandroidstudy.data.local.dao.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.local.entity.BookmarkEntity

@Database(
    entities = [BookmarkEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}
