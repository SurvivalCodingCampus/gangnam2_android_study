package com.survivalcoding.gangnam2kiandroidstudy.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ConnectivityNetworkMonitor(
    context: Context
) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val currentStatus: NetworkStatus
        get() = if (connectivityManager.activeNetwork != null) NetworkStatus.Available else NetworkStatus.Unavailable

    // Flow
    val networkStatus: Flow<NetworkStatus> = callbackFlow {

        // 구독하자마자 현재 네트워크 상태를 먼저 확인
        trySend(currentStatus) // 초기값 전송

        // 네트워크 변화를 감지할 콜백
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(NetworkStatus.Available)
            }

            override fun onLost(network: Network) {
                trySend(NetworkStatus.Unavailable)
            }
        }

        // 콜백 등록
        connectivityManager.registerDefaultNetworkCallback(callback)

        // Flow가 종료될 때(ViewModel이 clear될 때 등) 콜백 해제
        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }
}