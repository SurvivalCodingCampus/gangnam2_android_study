package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

class IngredientDataSourceImpl private constructor(
    appAssetManager: AppAssetManager
) : IngredientDataSource {
    companion object {
        @Volatile private var instance: IngredientDataSource? = null

        fun getInstance(appAssetManager: AppAssetManager) =
            instance ?: synchronized(this) {
                instance ?: IngredientDataSourceImpl(appAssetManager).also { instance = it }
            }
    }
}