package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.ProcedureDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure

fun ProcedureDto.toModel(): Procedure {
    return Procedure(
        recipeId = this.recipeId ?: -1,
        step = this.step ?: -1,
        content = this.content ?: "UNDEFINED"
    )
}