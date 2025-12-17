package com.survivalcoding.gangnam2kiandroidstudy.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProcedureDto(
    val recipeId: Int? = null,
    val step: Int? = null,
    val content: String? = null,
)
