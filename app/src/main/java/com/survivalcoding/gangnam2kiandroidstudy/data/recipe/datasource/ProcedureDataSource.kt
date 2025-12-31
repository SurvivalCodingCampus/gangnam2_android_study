package com.survivalcoding.gangnam2kiandroidstudy.data.recipe.datasource

import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.dto.ProcedureDTO

interface ProcedureDataSource {
    suspend fun getProcedures(): List<ProcedureDTO>
}