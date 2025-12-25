package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository

class FakeProcedureRepository : ProcedureRepository {
    override suspend fun getProcedures(): List<Procedure> {
        return emptyList()
    }
}
