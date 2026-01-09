package com.survivalcoding.gangnam2kiandroidstudy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class IngredientEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val image: String
)
