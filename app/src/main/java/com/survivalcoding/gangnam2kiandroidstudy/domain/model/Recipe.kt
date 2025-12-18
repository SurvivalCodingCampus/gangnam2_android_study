package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Recipe(
    val id: Int,
    val category: String,
    val name: String,
    val image: String,
    val chef: String,
    val time: String,
    val rating: Double,
    val ingredients: List<Ingredient>,
    val procedures: List<Procedure>,
)
