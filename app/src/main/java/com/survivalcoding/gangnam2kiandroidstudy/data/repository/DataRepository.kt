package com.survivalcoding.gangnam2kiandroidstudy.data.repository

interface DataRepository {
    suspend fun getData(): List<Int>
}