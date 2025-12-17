package com.survivalcoding.gangnam2kiandroidstudy.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class IngredientDto(
    val id: Int? = null,
    val name: String? = null,
    val image: String? = null,
)