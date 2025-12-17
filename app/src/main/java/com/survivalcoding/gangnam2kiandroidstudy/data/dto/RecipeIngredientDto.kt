package com.survivalcoding.gangnam2kiandroidstudy.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RecipeIngredientDto(
    val recipeId: Int? = null,
    val ingredientId: Int? = null,
    val amount: Int? = null,
)
