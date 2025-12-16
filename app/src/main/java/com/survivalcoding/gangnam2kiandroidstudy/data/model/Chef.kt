package com.survivalcoding.gangnam2kiandroidstudy.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Chef(
    val id: Int,
    val name: String,
    val image: String,
    val address: String
)