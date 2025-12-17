package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.ProcedureDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository

class ProcedureRepositoryImpl(
    private val procedureDataSource: ProcedureDataSource
) : ProcedureRepository {

    override suspend fun getProceduresByRecipeId(recipeId: Int): List<Procedure> {

        return procedureDataSource
            .getProceduresByRecipeId(recipeId)
            .sortedBy { it.step ?: Int.MAX_VALUE }
            .map { it.toModel() }
    }
}
