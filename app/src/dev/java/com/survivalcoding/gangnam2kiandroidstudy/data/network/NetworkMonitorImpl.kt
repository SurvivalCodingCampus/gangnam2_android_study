package com.survivalcoding.gangnam2kiandroidstudy.data.network

import android.content.Context
import com.survivalcoding.gangnam2kiandroidstudy.domain.network.NetworkEvent
import com.survivalcoding.gangnam2kiandroidstudy.domain.network.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class NetworkMonitorImpl(
    context: Context,
    scope: CoroutineScope,
) : NetworkMonitor {

    private val _isConnected = MutableStateFlow(true)
    override val isConnected: StateFlow<Boolean> = _isConnected

    private val _events = MutableSharedFlow<NetworkEvent>()
    override val events: SharedFlow<NetworkEvent> = _events

    fun setConnected(connected: Boolean) {
        _isConnected.value = connected
        _events.tryEmit(
            if (connected) NetworkEvent.Connected
            else NetworkEvent.Disconnected
        )
    }
}