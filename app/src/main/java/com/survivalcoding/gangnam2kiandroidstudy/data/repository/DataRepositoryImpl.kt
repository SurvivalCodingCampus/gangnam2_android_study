package com.survivalcoding.gangnam2kiandroidstudy.data.repository

class DataRepositoryImpl : DataRepository {
    override suspend fun getData(): List<Int> {
        return listOf(1, 2, 3)
    }
}