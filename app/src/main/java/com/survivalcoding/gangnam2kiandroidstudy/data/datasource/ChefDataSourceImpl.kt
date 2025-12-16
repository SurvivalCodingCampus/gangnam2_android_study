package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Chef
import com.survivalcoding.gangnam2kiandroidstudy.data.model.ProfilesResponse
import kotlinx.serialization.json.Json

class ChefDataSourceImpl private constructor(
    appAssetManager: AppAssetManager
) : ChefDataSource {
    val chefsJsonString = appAssetManager.open("chef.json").use { input ->
        input.readBytes().toString(Charsets.UTF_8)
    }

    override suspend fun getAllChefs(): List<Chef> {
        val response = Json.decodeFromString<ProfilesResponse>(chefsJsonString)
        return response.profiles
    }

    companion object {
        @Volatile private var instance: ChefDataSource? = null

        fun getInstance(appAssetManager: AppAssetManager) =
            instance ?: synchronized(this) {
                instance ?: ChefDataSourceImpl(appAssetManager).also { instance = it }
            }
    }
}