package com.survivalcoding.gangnam2kiandroidstudy.domain.model

data class Recipe(
    val id: Long,
    val category: RecipeCategory,
    val name: String,
    val imageUrl: String,
    val chef: String,
    val time: String,
    val rating: Double,
    val ingredients: List<IngredientAmount>,
)
