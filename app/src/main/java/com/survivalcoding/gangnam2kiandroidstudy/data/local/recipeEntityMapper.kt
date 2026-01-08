package com.survivalcoding.gangnam2kiandroidstudy.data.local

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDTO
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun RecipeDTO.toEntity(): RecipeEntity? {
    if (id == null) return null
    val json = Json { ignoreUnknownKeys = true }
    return RecipeEntity(
        id = id,
        category = category ?: "",
        name = name ?: "",
        image = image ?: "",
        chef = chef ?: "",
        time = time ?: "",
        rating = rating ?: 0.0,
        ingredientsJson = json.encodeToString(ingredients ?: emptyList())
    )
}

fun RecipeEntity.toDTO(): RecipeDTO {
    val json = Json { ignoreUnknownKeys = true }
    return RecipeDTO(
        id = id,
        category = category,
        name = name,
        image = image,
        chef = chef,
        time = time,
        rating = rating,
        ingredients = try {
            json.decodeFromString(ingredientsJson)
        } catch (e: Exception) {
            emptyList()
        }
    )
}
