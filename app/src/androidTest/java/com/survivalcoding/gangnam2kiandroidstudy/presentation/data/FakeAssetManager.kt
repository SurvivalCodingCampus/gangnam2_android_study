package com.survivalcoding.gangnam2kiandroidstudy.presentation.data

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AppAssetManager
import java.io.ByteArrayInputStream
import java.io.InputStream

class FakeAssetManager(private val jsonString: String) : AppAssetManager {
    override fun open(fileName: String): InputStream {
        return ByteArrayInputStream(jsonString.toByteArray())
    }
}