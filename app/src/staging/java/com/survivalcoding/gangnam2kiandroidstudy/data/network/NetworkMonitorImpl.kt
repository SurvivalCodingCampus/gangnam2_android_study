package com.survivalcoding.gangnam2kiandroidstudy.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.survivalcoding.gangnam2kiandroidstudy.domain.network.NetworkEvent
import com.survivalcoding.gangnam2kiandroidstudy.domain.network.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// 실제 작동 로직 + + override 시 실제 콜백 무시
class NetworkMonitorImpl(
    context: Context,
    scope: CoroutineScope,
) : NetworkMonitor {

    private val connectivityManager =
        context.getSystemService(ConnectivityManager::class.java)

    private val _events = MutableSharedFlow<NetworkEvent>(
        replay = 0,
        extraBufferCapacity = 1
    )
    override val events: SharedFlow<NetworkEvent> = _events

    private val overrideState = MutableStateFlow<Boolean?>(null)

    override val isConnected: StateFlow<Boolean> =
        callbackFlow {

            var lastConnected: Boolean = true

            fun emit(connected: Boolean) {
                trySend(connected)

                if (lastConnected != connected) {
                    _events.tryEmit(
                        if (connected) NetworkEvent.Connected
                        else NetworkEvent.Disconnected
                    )
                }
                lastConnected = connected
            }

            // 실제 네트워크 콜백
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    if (overrideState.value == null) emit(true)
                }

                override fun onLost(network: Network) {
                    if (overrideState.value == null) emit(false)
                }
            }

            val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()

            connectivityManager.registerNetworkCallback(request, callback)

            // 초기 상태
            val active = connectivityManager.activeNetwork
            val connected = active != null &&
                    connectivityManager.getNetworkCapabilities(active)
                        ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true

            emit(connected)

            // QA override 반영
            val job = launch {
                overrideState.filterNotNull().collect { forced ->
                    emit(forced)
                }
            }

            awaitClose {
                job.cancel()
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }
            .distinctUntilChanged()
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(5_000),
                true,
            )

    /** QA 전용 API */
    fun forceConnected(value: Boolean?) {
        overrideState.value = value
    }
}
