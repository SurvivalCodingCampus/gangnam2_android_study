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
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn

/**
 * NetworkMonitor (Singleton)의 역할
 *
 * ConnectivityManager를 감싼다
 * 전통적인 콜백 → Flow 로 변환
 * 앱 전역에서 공유 가능
 **/
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

    override val isConnected: StateFlow<Boolean> =
        callbackFlow {

            // 초기 상태를 ON으로 가정
            // 비연결시 onLost -> 비연결 스낵바
            // 재연결시 onAvailable -> 연결 스낵바
            var lastConnected = true

            fun emit(connected: Boolean) {
                trySend(connected)

                if (connected != lastConnected) {
                    _events.tryEmit(
                        if (connected) NetworkEvent.Connected
                        else NetworkEvent.Disconnected
                    )
                    lastConnected = connected
                }
            }

            // 👇 1. 전통적인 콜백
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    emit(true)
                }

                override fun onLost(network: Network) {
                    emit(false)
                }
            }

            // 👇 2. 콜백 등록
            val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()

            connectivityManager.registerNetworkCallback(request, callback)

            // 초기 상태 판단
            val active = connectivityManager.activeNetwork
            val connected = active != null &&
                    connectivityManager.getNetworkCapabilities(active)
                        ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true

            emit(connected)     // 초기 진입시 연결 상태

            // 👇 3. Flow 종료 시 정리
            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }
            // 👇 4. Flow → StateFlow (Singleton 공유)
            .distinctUntilChanged()
            .stateIn(
                scope,
                SharingStarted.Companion.WhileSubscribed(5_000),
                true, // State 초기값도 ON
            )
}