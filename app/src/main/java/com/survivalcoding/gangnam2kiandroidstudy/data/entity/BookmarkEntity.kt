package com.survivalcoding.gangnam2kiandroidstudy.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "bookmark",
    primaryKeys = ["recipe_id", "profile_id"],
)
data class BookmarkEntity(
    @ColumnInfo(name = "recipe_id") val recipeId: Long,
    @ColumnInfo(name = "profile_id") val profileId: Long,
)
