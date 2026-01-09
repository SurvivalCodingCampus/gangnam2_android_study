package com.survivalcoding.gangnam2kiandroidstudy.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey
    val id: Long,
    val category: String,
    val name: String,
    val image: String,
    val chef: String,
    val time: String,
    val rating: Double,
    val ingredientsJson: String // List<IngredientAmountDTO>를 JSON으로 저장
)
