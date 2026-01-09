package com.survivalcoding.gangnam2kiandroidstudy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val image: String,
    val category: String,
    val chef: String,
    val time: String,
    val rating: Double,
    val address: String,
    val createdAt: Long
)
