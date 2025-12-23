package com.survivalcoding.gangnam2kiandroidstudy.core.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface NetworkMonitor {
    val events: Flow<NetworkEvent>
    val status: StateFlow<NetworkStatus>
}