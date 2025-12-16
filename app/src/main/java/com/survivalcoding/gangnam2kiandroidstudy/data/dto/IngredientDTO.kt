package com.survivalcoding.gangnam2kiandroidstudy.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class IngredientDTO(
    val id: Long? = null,
    val name: String? = null,
    val imageUrl: String? = null,
)