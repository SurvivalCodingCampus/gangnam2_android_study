package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

class ProcedureDataSourceImpl private constructor(
    appAssetManager: AppAssetManager
) : ProcedureDataSource {
    companion object {
        @Volatile private var instance: ProcedureDataSource? = null

        fun getInstance(appAssetManager: AppAssetManager) =
            instance ?: synchronized(this) {
                instance ?: ProcedureDataSourceImpl(appAssetManager).also { instance = it }
            }
    }
}