package com.survivalcoding.gangnam2kiandroidstudy.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ChefDto(
    val id: Int? = null,
    val name: String? = null,
    val image: String? = null,
    val address: String? = null,
)