package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.ProcedureDto

interface ProcedureDataSource {
    suspend fun getProcedures(): List<ProcedureDto>
}