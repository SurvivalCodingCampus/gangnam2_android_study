package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import android.content.res.AssetManager

class AppAssetManagerImpl(private val assetManager: AssetManager) : AppAssetManager {
    override fun open(fileName: String) = assetManager.open(fileName)
}