package com.survivalcoding.gangnam2kiandroidstudy.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientXDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("name")
    val name: String? = null
)