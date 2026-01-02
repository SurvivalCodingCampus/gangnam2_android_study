package com.survivalcoding.gangnam2kiandroidstudy.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey val recipeId: Int
)