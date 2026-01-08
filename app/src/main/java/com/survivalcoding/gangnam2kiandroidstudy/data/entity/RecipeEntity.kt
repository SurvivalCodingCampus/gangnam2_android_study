package com.survivalcoding.gangnam2kiandroidstudy.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey val id: Long,
    val name: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    val chef: String,
    val time: Int,
    val rating: Double,
    val serve: Int,
    @ColumnInfo(name = "chef_image_url") val chefImageUrl: String,
    @ColumnInfo(name = "share_url") val shareUrl: String,
)