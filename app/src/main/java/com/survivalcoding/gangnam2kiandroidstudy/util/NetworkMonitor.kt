package com.survivalcoding.gangnam2kiandroidstudy.util

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val networkState: Flow<Boolean>
    fun isConnected(): Boolean
}