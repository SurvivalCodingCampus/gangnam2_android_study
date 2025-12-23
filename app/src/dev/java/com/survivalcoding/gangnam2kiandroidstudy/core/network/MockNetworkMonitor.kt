package com.survivalcoding.gangnam2kiandroidstudy.core.network

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockNetworkMonitor @Inject constructor() : NetworkMonitor {

    init {
        Log.d("NetworkMonitor", "MockNetworkMonitor injected")
    }

    private val _status = MutableStateFlow(NetworkStatus.Available)
    override val status: StateFlow<NetworkStatus> = _status

    private val _events = MutableSharedFlow<NetworkEvent>(
        replay = 0,
        extraBufferCapacity = 1
    )
    override val events = _events.asSharedFlow()

    // 테스트용 제어 함수
    fun setConnected() {
        _status.value = NetworkStatus.Available
        emitEvent(NetworkEvent.Connected)
        Log.d("MockNetwork", "emit Connected")
    }

    fun setDisconnected() {
        _status.value = NetworkStatus.Unavailable
        emitEvent(NetworkEvent.Disconnected)
        Log.d("MockNetwork", "emit Disconnected")
    }

    private fun emitEvent(event: NetworkEvent) {
        CoroutineScope(Dispatchers.Main).launch {
            _events.emit(event)
        }
    }
}