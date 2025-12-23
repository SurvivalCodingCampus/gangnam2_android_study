package com.survivalcoding.gangnam2kiandroidstudy.domain.network

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val isConnectedNetwork: Flow<Boolean>
}