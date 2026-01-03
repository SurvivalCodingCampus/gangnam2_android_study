package com.misterjerry.gangnam2kiandroidstudy.data.data_source.procedure

import com.misterjerry.gangnam2kiandroidstudy.domain.model.Procedures

interface ProcedureDataSource {
    suspend fun getAllProcedures(): Procedures
}