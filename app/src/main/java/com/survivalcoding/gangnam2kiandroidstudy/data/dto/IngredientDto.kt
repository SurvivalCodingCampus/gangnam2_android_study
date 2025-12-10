package com.survivalcoding.gangnam2kiandroidstudy.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientDto(
    @SerialName("amount")
    val amount: Int? = 0,
    @SerialName("ingredient")
    val ingredient: IngredientXDto? = IngredientXDto()
)