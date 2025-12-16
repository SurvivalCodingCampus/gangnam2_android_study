package com.survivalcoding.gangnam2kiandroidstudy.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Procedure(
    val recipeId: Int,
    val step: Int,
    val content: String
)