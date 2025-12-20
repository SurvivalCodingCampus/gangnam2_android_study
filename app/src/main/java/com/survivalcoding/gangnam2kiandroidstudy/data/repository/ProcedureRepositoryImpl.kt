package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ProcedureDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository

class ProcedureRepositoryImpl private constructor(
    private val procedureDataSource: ProcedureDataSource
) : ProcedureRepository {
    override suspend fun getProcedureByRecipeId(recipeId: Int): List<Procedure> {
        return procedureDataSource.getAllProcedure()
            .filter { it.recipeId == recipeId }
    }

    companion object {
        @Volatile private var instance: ProcedureRepository? = null

        fun getInstance(procedureDataSource: ProcedureDataSource) =
            instance ?: synchronized(this) {
                instance ?: ProcedureRepositoryImpl(procedureDataSource).also { instance = it }
            }
    }
}