package com.survivalcoding.gangnam2kiandroidstudy.data.recipe.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.dto.ProcedureDTO
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure

fun ProcedureDTO.toModel(): Procedure {
    return Procedure(
        recipeId = recipeId ?: 0,
        step = step ?: 0,
        content = content ?: ""
    )
}