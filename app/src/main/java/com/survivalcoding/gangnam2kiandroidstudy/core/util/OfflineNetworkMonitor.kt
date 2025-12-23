package com.survivalcoding.gangnam2kiandroidstudy.core.util

import kotlinx.coroutines.flow.flowOf

object OfflineNetworkMonitor : NetworkMonitor {

    override val networkState = flowOf(false)

    override fun isConnected(): Boolean {
        return false
    }
}