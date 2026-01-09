package com.survivalcoding.gangnam2kiandroidstudy.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey val id: Int,
    val category: String,
    val name: String,
    val image: String,
    val chef: String,
    val time: String,
    val rating: Double,
)
