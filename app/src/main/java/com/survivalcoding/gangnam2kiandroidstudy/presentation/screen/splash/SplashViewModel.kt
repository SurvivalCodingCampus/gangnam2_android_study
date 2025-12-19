package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.network.NetworkEvent
import com.survivalcoding.gangnam2kiandroidstudy.domain.network.NetworkMonitor
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val networkMonitor: NetworkMonitor,
) : ViewModel() {
    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SplashEvent>()
    val event = _event.asSharedFlow()

    fun onAction(action: SplashAction) {
        when (action) {
            SplashAction.OnStartClick -> {
                viewModelScope.launch {
                    _event.emit(SplashEvent.NavigateToSignIn)
                }
            }
        }
    }

    init {
        // NetworkMonitor를 구독하기
        observeNetworkState()
        observeNetworkEvent()
    }

    // 네트워크 상태 구독
    private fun observeNetworkState() {
        viewModelScope.launch {
            networkMonitor.isConnected.collect { connected ->
                _state.value = _state.value.copy(
                    isNetworkConnected = connected
                )
            }
        }
    }

    // 네트워크 이벤트 구독
    private fun observeNetworkEvent() {
        viewModelScope.launch {
            networkMonitor.events.collect { event ->
                when (event) {
                    NetworkEvent.Connected -> {
                        _event.emit(SplashEvent.SnackBarNetworkConnected)
                    }

                    NetworkEvent.Disconnected -> {
                        _event.emit(SplashEvent.SnackBarNetworkDisconnected)
                    }
                }
            }
        }
    }
}