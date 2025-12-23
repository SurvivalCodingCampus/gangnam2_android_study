package com.survivalcoding.gangnam2kiandroidstudy.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NetworkStatus
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.NetworkStatusRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(
    private val networkStatusRepository: NetworkStatusRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SplashEvent>()
    val event = _event.asSharedFlow()

    init {
        observeNetworkStatus()
    }

    fun onAction(action: SplashAction) {
        when (action) {
            SplashAction.OnStartClick -> {
                viewModelScope.launch {
                    _event.emit(SplashEvent.NavigateToSignIn)
                }
            }
        }
    }


    private fun observeNetworkStatus() {
        viewModelScope.launch {
            networkStatusRepository.observeNetworkStatus().collect { networkStatus ->
                when (networkStatus) {
                    NetworkStatus.Connected -> {
                        _event.emit(
                            SplashEvent.ShowSnackBar(
                                "네트워크가 연결되었습니다."
                            )
                        )
                        _state.update {
                            it.copy(isConnected = true)
                        }
                    }

                    NetworkStatus.Disconnected -> {
                        _event.emit(
                            SplashEvent.ShowSnackBar(
                                "네트워크가 연결되어있지 않습니다."
                            )
                        )
                        _state.update {
                            it.copy(isConnected = false)
                        }
                    }
                }
            }
        }


    }
}