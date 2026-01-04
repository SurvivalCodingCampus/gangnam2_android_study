package com.survivalcoding.gangnam2kiandroidstudy.presentation.qa

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.data.network.NetworkMonitorImpl

@Composable
fun QaNetworkPanel(monitor: NetworkMonitorImpl) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.7f))
                .padding(12.dp)
        ) {
            Button(onClick = { monitor.forceConnected(true) }) {
                Text("Force Connected")
            }
            Button(onClick = { monitor.forceConnected(false) }) {
                Text("Force Disconnected")
            }
            Button(onClick = { monitor.forceConnected(null) }) {
                Text("Use Real Network")
            }
        }
    }
}
