package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import java.io.InputStream

interface AppAssetManager {
    fun open(fileName: String): InputStream
}