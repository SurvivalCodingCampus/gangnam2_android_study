package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository

class MockProcedureRepository : ProcedureRepository {
    override suspend fun getProcedures(id: Int): List<Procedure> {
        return emptyList()
    }
}
