package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeIngredientDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredient

fun RecipeIngredientDto.toModel(): RecipeIngredient {
    return RecipeIngredient(
        recipeId = this.recipeId ?: -1,
        ingredientId = this.ingredientId ?: -1,
        amount = this.amount ?: 0
    )
}