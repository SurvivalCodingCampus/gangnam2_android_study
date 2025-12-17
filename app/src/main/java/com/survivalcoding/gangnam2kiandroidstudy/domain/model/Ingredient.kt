package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    val id: Int,
    val image: String,
    val name: String
)