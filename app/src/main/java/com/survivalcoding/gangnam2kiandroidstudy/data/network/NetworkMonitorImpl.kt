package com.survivalcoding.gangnam2kiandroidstudy.data.network

import android.net.ConnectivityManager
import android.net.Network
import com.survivalcoding.gangnam2kiandroidstudy.domain.network.NetworkMonitor
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkMonitorImpl(
    private val connectivityManager: ConnectivityManager,
) : NetworkMonitor {
    override val isConnectedNetwork: Flow<Boolean> = callbackFlow {
        val result = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(true)
            }

            override fun onLost(network: Network) {
                trySend(false)
            }

        }
        connectivityManager.registerDefaultNetworkCallback(result)

        val isInitialConnected = connectivityManager.activeNetwork != null
        trySend(isInitialConnected)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(result)
        }
    }
}