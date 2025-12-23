package com.survivalcoding.gangnam2kiandroidstudy.core.util

import kotlinx.coroutines.flow.flowOf

object OnlineNetworkMonitor : NetworkMonitor {

    override val networkState = flowOf(true)

    override fun isConnected(): Boolean {
        return true
    }
}