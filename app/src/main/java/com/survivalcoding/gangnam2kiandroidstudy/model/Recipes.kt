package com.survivalcoding.gangnam2kiandroidstudy.model

data class Recipes(
    val category: String,
    val chef: String,
    val id: Int,
    val image: String,
    val name: String,
    val rating: Double,
    val time: String
)