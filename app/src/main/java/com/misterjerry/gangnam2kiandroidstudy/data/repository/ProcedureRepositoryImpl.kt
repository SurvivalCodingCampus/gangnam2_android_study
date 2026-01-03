package com.misterjerry.gangnam2kiandroidstudy.data.repository

import com.misterjerry.gangnam2kiandroidstudy.data.data_source.procedure.ProcedureDataSource
import com.misterjerry.gangnam2kiandroidstudy.domain.model.Procedure
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.ProcedureRepository

class ProcedureRepositoryImpl(
    private val procedureDataSource: ProcedureDataSource
) : ProcedureRepository {
    override suspend fun getProcedureByRecipeId(id: Int): List<Procedure> {
        return procedureDataSource.getAllProcedures().procedures.filter { it.recipeId == id }
    }
}