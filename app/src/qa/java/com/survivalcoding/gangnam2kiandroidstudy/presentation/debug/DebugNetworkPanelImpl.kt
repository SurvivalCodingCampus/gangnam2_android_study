package com.survivalcoding.gangnam2kiandroidstudy.presentation.debug

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.core.network.MockNetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors

@Composable
fun DebugNetworkPanelImpl(
    modifier: Modifier = Modifier,
    networkMonitor: MockNetworkMonitor
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .background(AppColors.black.copy(alpha = 0.7f))
            .padding(12.dp)
    ) {
        Text(" Network Qa Panel", color = AppColors.white)

        Spacer(Modifier.height(8.dp))

        Button(onClick = { networkMonitor.setConnected() }) {
            Text("QA Connected")
        }

        Spacer(Modifier.height(4.dp))

        Button(onClick = { networkMonitor.setDisconnected() }) {
            Text("QA Disconnected")
        }
    }
}
