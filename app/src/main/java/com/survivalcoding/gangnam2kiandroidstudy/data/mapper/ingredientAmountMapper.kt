package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.IngredientAmountDTO
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.IngredientAmount

fun IngredientAmountDTO.toModel(): IngredientAmount? {
    val ingredientModel = ingredient?.toModel()
    val amountValue = amount

    if (ingredientModel == null || amountValue == null || ingredientModel.id == 0L) return null

    return IngredientAmount(
        ingredient = ingredientModel,
        amount = amountValue,
    )
}