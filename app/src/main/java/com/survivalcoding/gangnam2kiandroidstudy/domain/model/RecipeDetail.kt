package com.survivalcoding.gangnam2kiandroidstudy.domain.model

data class RecipeDetail(
    val recipe: Recipe,
    val ingredients: List<Ingrident>,
    val procedures: List<Procedure>
)
