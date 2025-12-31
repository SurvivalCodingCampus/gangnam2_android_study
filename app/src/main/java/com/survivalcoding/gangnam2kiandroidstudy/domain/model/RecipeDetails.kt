package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.recipe.Recipe

data class RecipeDetails(
    val recipe: Recipe,
    val ingredients: List<RecipeIngredient>,
    val procedures: List<Procedure>,
)
