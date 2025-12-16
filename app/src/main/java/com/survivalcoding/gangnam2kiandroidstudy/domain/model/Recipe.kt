package com.survivalcoding.gangnam2kiandroidstudy.domain.model

data class Recipe(
    val id: Int,
    val category: String,
    val name: String,
    val image: String,
    val chef: String,
    val time: String,
    val rating: Double,
    val ingridents: List<Ingrident>,
    val procedures: List<Procedure>,
)
