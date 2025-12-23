package com.survivalcoding.gangnam2kiandroidstudy.core.util

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val networkState: Flow<Boolean>
    fun isConnected(): Boolean
}