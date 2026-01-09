package com.survivalcoding.gangnam2kiandroidstudy.data.local

import androidx.room.*
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.IngredientAmountDTO
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RecipeTypeConverters {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromIngredients(value: List<IngredientAmountDTO?>?): String {
        return json.encodeToString(value ?: emptyList())
    }

    @TypeConverter
    fun toIngredients(value: String): List<IngredientAmountDTO?>? {
        return try {
            json.decodeFromString<List<IngredientAmountDTO?>>(value)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
