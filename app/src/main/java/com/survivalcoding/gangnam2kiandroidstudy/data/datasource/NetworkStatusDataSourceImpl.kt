package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.NetworkStatusDto
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkStatusDataSourceImpl(
    private val context: Context
) : NetworkStatusDataSource {
    override fun observeNetworkStatus(): Flow<NetworkStatusDto> = callbackFlow {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(NetworkStatusDto(isConnected = true))
            }

            override fun onLost(network: Network) {
                trySend(NetworkStatusDto(isConnected = false))
            }
        }

        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, callback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }
}