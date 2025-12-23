package com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AppAssetManager
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ChefDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Chef
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.dto.ProfilesResponse
import kotlinx.serialization.json.Json

class ChefDataSourceImpl(
    appAssetManager: AppAssetManager
) : ChefDataSource {
    val chefsJsonString = appAssetManager.open("chef.json").use { input ->
        input.readBytes().toString(Charsets.UTF_8)
    }

    override suspend fun getAllChefs(): List<Chef> {
        val response = Json.Default.decodeFromString<ProfilesResponse>(chefsJsonString)
        return response.profiles
    }
}