package com.survivalcoding.gangnam2kiandroidstudy.data.recipe.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.datasource.ProcedureDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository

class ProcedureRepositoryImpl(
    private val procedureDataSource: ProcedureDataSource
) : ProcedureRepository {
    override suspend fun getProcedures(recipeId: Int): List<Procedure> {
        return procedureDataSource.getProcedures().filter { it.recipeId == recipeId }
            .map { it.toModel() }
    }
}