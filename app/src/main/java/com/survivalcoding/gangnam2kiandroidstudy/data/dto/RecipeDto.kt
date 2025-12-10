package com.survivalcoding.gangnam2kiandroidstudy.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    @SerialName("category")
    val category: String? = "",
    @SerialName("chef")
    val chef: String? = "",
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("image")
    val image: String? = "",
    @SerialName("ingredients")
    val ingredients: List<IngredientDto>? = listOf(),
    @SerialName("name")
    val name: String? = "",
    @SerialName("rating")
    val rating: Double? = 0.0,
    @SerialName("time")
    val time: String? = ""
)