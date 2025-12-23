package com.survivalcoding.gangnam2kiandroidstudy.data.recipe.network

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import com.survivalcoding.gangnam2kiandroidstudy.domain.network.NetworkMonitor
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkMonitorImpl(
    private val connectivityManager: ConnectivityManager
) : NetworkMonitor {

    override val isConnected: Flow<Boolean> = callbackFlow {

        // 초기 상태
        val initialConnected =
            connectivityManager.activeNetwork != null

        trySend(initialConnected)

        val callback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(network)

                val hasInternet =
                    capabilities?.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_INTERNET
                    ) == true

                trySend(hasInternet)
            }

            override fun onLost(network: Network) {
                trySend(false)
            }
        }

        connectivityManager.registerDefaultNetworkCallback(callback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }
}
