package com.survivalcoding.gangnam2kiandroidstudy.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class IngredientListDto(
    @SerialName("amount")
    val amount: Int?,
    @SerialName("ingredient")
    val ingredient: IngredientDto?
)