package com.survivalcoding.gangnam2kiandroidstudy.data.data_source.procedure

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedures

interface ProcedureDataSource {
    suspend fun getAllProcedures(): Procedures
}