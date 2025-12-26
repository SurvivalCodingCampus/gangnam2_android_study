package com.survivalcoding.gangnam2kiandroidstudy.core.network

import com.survivalcoding.gangnam2kiandroidstudy.core.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkMonitorImpl @Inject constructor(
    connectivity: ConnectivityNetworkMonitor,
    @ApplicationScope private val applicationScope: CoroutineScope
) : NetworkMonitor {

    private val _status = MutableStateFlow(connectivity.currentStatus)
    override val status: StateFlow<NetworkStatus> = _status

    private val _events = MutableSharedFlow<NetworkEvent>(
        replay = 0,
        extraBufferCapacity = 1
    )
    override val events = _events.asSharedFlow()

    init {
        applicationScope.launch {
            var lastStatus: NetworkStatus? = null

            connectivity.networkStatus.collect { current ->
                _status.value = current

                if (lastStatus != null && lastStatus != current) {
                    _events.emit(
                        if (current == NetworkStatus.Available)
                            NetworkEvent.Connected
                        else
                            NetworkEvent.Disconnected
                    )
                }

                lastStatus = current
            }
        }
    }
}