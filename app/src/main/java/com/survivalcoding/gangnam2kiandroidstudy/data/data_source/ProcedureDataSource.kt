package com.survivalcoding.gangnam2kiandroidstudy.data.data_source

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.ProcedureDto

interface ProcedureDataSource {
    suspend fun getProceduresByRecipeId(recipeId: Int): List<ProcedureDto>
}
