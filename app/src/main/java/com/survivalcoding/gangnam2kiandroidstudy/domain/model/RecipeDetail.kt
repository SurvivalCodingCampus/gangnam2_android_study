package com.survivalcoding.gangnam2kiandroidstudy.domain.model

data class RecipeDetail(
    val recipe: Recipe,
    val chef: Chef?,
    val ingredients: List<IngredientItem>,
    val procedures: List<Procedure>
)
