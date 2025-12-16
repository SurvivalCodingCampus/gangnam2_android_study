package com.survivalcoding.gangnam2kiandroidstudy.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class IngredientAmountDTO(
    val ingredient: IngredientDTO? = null,
    val amount: Int? = null,
)