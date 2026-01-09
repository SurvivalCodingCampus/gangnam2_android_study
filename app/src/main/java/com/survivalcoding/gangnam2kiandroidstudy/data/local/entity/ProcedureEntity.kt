package com.survivalcoding.gangnam2kiandroidstudy.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "procedures",
    foreignKeys = [
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = ["id"],
            childColumns = ["recipeId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ProcedureEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val recipeId: Int,
    val step: Int,
    val content: String
)
