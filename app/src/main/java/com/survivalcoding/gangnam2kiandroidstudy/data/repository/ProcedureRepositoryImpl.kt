package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ProcedureDataSource

class ProcedureRepositoryImpl private constructor(
    procedureDataSource: ProcedureDataSource
) : ProcedureRepository {

    companion object {
        @Volatile private var instance: ProcedureRepository? = null

        fun getInstance(procedureDataSource: ProcedureDataSource) =
            instance ?: synchronized(this) {
                instance ?: ProcedureRepositoryImpl(procedureDataSource).also { instance = it }
            }
    }
}