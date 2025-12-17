package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Procedure(
    val content: String,
    val recipeId: Int,
    val step: Int
)