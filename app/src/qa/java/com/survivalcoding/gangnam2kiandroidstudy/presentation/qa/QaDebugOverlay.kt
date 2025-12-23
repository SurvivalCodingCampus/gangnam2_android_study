package com.survivalcoding.gangnam2kiandroidstudy.presentation.qa

import androidx.compose.runtime.Composable
import com.survivalcoding.gangnam2kiandroidstudy.data.network.NetworkMonitorImpl

@Composable
fun QaDebugOverlay(
    monitor: NetworkMonitorImpl?
) {
    if (monitor == null) return
    QaNetworkPanel(monitor)
}