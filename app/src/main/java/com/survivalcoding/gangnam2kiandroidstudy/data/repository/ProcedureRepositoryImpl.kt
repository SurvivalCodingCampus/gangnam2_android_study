package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ProcedureDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository

class ProcedureRepositoryImpl(
    private val procedureDataSource: ProcedureDataSource
) : ProcedureRepository {
    override suspend fun getProcedureByRecipeId(recipeId: Int): List<Procedure> {
        return procedureDataSource.getAllProcedure()
            .filter { it.recipeId == recipeId }
    }
}