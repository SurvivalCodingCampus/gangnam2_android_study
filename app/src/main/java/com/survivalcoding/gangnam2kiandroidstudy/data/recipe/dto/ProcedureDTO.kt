package com.survivalcoding.gangnam2kiandroidstudy.data.recipe.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProcedureDTO(
    @SerialName("recipeId")
    val recipeId: Int?,
    @SerialName("step")
    val step: Int?,
    @SerialName("content")
    val content: String?
)