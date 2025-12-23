package com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AppAssetManager
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ProcedureDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.dto.ProceduresResponse
import kotlinx.serialization.json.Json

class ProcedureDataSourceImpl(
    appAssetManager: AppAssetManager
) : ProcedureDataSource {
    val proceduresJsonString = appAssetManager.open("procedure.json").use { input ->
        input.readBytes().toString(Charsets.UTF_8)
    }

    override fun getAllProcedure(): List<Procedure> {
        val response = Json.Default.decodeFromString<ProceduresResponse>(proceduresJsonString)
        return response.procedures
    }
}