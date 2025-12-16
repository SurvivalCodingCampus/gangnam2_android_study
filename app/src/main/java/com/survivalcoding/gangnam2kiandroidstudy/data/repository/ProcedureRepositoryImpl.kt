package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AssetDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository

class ProcedureRepositoryImpl(
    private val dataSource: AssetDataSource
) : ProcedureRepository {

    override suspend fun getProcedures(id: Int): List<Procedure> {
        return dataSource.getProcedures().procedures
            ?.filter { it.recipeId == id }
            ?.map { it.toModel() }
            ?: emptyList()
    }
}