package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.ProcedureDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure

fun ProcedureDto.toModel(): Procedure {
    return Procedure(
        recipeId = recipeId ?: 0,
        step = step ?: 0,
        content = content ?: ""
    )
}