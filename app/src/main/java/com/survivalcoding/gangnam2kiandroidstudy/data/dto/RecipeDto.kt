package com.survivalcoding.gangnam2kiandroidstudy.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    @SerialName("category")
    val category: String?,
    @SerialName("chef")
    val chef: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("image")
    val image: String?,
    @SerialName("ingredients")
    val ingredients: List<IngredientListDto>?,
    @SerialName("name")
    val name: String?,
    @SerialName("rating")
    val rating: Double?,
    @SerialName("time")
    val time: String?,
    @SerialName("createdAt")
    val createdAt: Long?,
    @SerialName("address")
    val address: String?,
)