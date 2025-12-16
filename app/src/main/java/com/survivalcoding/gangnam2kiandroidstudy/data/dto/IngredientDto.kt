package com.survivalcoding.gangnam2kiandroidstudy.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientDto(
    val id: Int? = null,
    val name: String? = null,
    val image: String? = null,
    val weight: String? = null,
    @SerialName("recipe_id")
    val recipeId: Int? = null
)

@Serializable
data class IngredientResponse(val ingredients: List<IngredientDto>? = null)