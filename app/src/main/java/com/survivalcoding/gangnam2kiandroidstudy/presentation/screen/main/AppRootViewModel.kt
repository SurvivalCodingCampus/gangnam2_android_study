package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.network.NetworkEvent
import com.survivalcoding.gangnam2kiandroidstudy.core.network.NetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.core.network.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppRootViewModel @Inject constructor(
    val networkMonitor: NetworkMonitor
) : ViewModel() {

    // / replay = 1을 주어, 나중에 구독한 UI도 마지막 상태(에러 등)를 받을 수 있게함
    private val _networkEvents = MutableSharedFlow<NetworkEvent>(replay = 1)
    val networkEvents = _networkEvents.asSharedFlow()

    init {
        viewModelScope.launch {
            // 앱 최초 진입 시 1회 판단
            if (networkMonitor.status.value == NetworkStatus.Unavailable) {
                _networkEvents.emit(NetworkEvent.Disconnected)
            }


            // 이후 변화 전달
            networkMonitor.events.collect {
                _networkEvents.emit(it)
            }
        }
    }
}

