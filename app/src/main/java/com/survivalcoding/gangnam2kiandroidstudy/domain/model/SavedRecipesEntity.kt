package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedRecipesEntity(
    @PrimaryKey @ColumnInfo(name = "id") val recipeId: Int,
)