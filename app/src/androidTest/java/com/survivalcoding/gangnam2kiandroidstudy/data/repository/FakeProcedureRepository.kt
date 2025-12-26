package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository

class FakeProcedureRepository : ProcedureRepository {
    override suspend fun getProcedure(recipeId: Int): List<Procedure> {
        return listOf(
            Procedure(recipeId, 1, "Wash the meat"),
            Procedure(recipeId, 2, "Cook it")
        )
    }
}
