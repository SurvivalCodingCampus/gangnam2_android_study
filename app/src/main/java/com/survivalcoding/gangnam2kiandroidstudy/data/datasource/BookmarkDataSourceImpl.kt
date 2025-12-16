package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

class BookmarkDataSourceImpl private constructor(
    appAssetManager: AppAssetManager
) : BookmarkDataSource {
    companion object {
        @Volatile private var instance: BookmarkDataSource? = null

        fun getInstance(appAssetManager: AppAssetManager) =
            instance ?: synchronized(this) {
                instance ?: BookmarkDataSourceImpl(appAssetManager).also { instance = it }
            }
    }
}