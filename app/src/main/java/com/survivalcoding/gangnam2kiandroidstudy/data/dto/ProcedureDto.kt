package com.survivalcoding.gangnam2kiandroidstudy.data.dto

import kotlinx.serialization.SerialName

data class ProcedureDto(
    @SerialName("recipeId")
    val recipeId: Int?,
    @SerialName("step")
    val step: Int?,
    @SerialName("content")
    val content: String?
)
