package com.survivalcoding.gangnam2kiandroidstudy.presentation.debug

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.survivalcoding.gangnam2kiandroidstudy.core.network.MockNetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.core.network.NetworkMonitor


@Composable
fun DebugNetworkPanel(
    modifier: Modifier = Modifier,
    networkMonitor: NetworkMonitor
) {
    if (networkMonitor is MockNetworkMonitor) {
        DebugNetworkPanelImpl(
            modifier = modifier,
            networkMonitor = networkMonitor
        )
    }
}
