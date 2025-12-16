package com.survivalcoding.gangnam2kiandroidstudy.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeIngredientEntity(
    val recipeId: Int,
    val ingredientId: Int,
    val amount: Int
)