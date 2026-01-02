package com.survivalcoding.gangnam2kiandroidstudy.data.bookmark.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.survivalcoding.gangnam2kiandroidstudy.data.bookmark.dao.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.bookmark.entity.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}
