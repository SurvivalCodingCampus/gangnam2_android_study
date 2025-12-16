package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.data.model.ProcedureResponse
import kotlinx.serialization.json.Json

class ProcedureDataSourceImpl private constructor(
    appAssetManager: AppAssetManager
) : ProcedureDataSource {
    val proceduresJsonString = appAssetManager.open("procedure.json").use { input ->
        input.readBytes().toString(Charsets.UTF_8)
    }

    override fun getAllProcedure(): List<Procedure> {
        val response = Json.decodeFromString<ProcedureResponse>(proceduresJsonString)
        return response.procedures
    }

    companion object {
        @Volatile private var instance: ProcedureDataSource? = null

        fun getInstance(appAssetManager: AppAssetManager) =
            instance ?: synchronized(this) {
                instance ?: ProcedureDataSourceImpl(appAssetManager).also { instance = it }
            }
    }
}