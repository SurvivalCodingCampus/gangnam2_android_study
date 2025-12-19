package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.util.NetworkMonitor
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(
    private val networkMonitor: NetworkMonitor,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<SplashEvent>()
    val event = _event.asSharedFlow()

    init {
        getNetworkState()
    }

    fun onAction(action: SplashAction) {
        when (action) {
            SplashAction.OnClick -> navigateToSignIn()
        }
    }

    private fun navigateToSignIn() {
        onEvent(SplashEvent.NavigateToSignIn)
    }

    private fun getNetworkState() {
        viewModelScope.launch {
            networkMonitor.networkState.collectIndexed { index, isConnected ->
                if (isConnected) {
                    if (index > 0) {
                        showSnackbar("네트워크에 재연결되었습니다")
                    }
                } else {
                    showSnackbar("네트워크에 연결되지 않았습니다")
                }
                changeNetworkState(isConnected)
            }
        }
    }

    private fun showSnackbar(message: String) {
        onEvent(SplashEvent.ShowSnackbar(message))
    }

    private fun changeNetworkState(isConnected: Boolean) {
        _uiState.update {
            it.copy(isOnline = isConnected)
        }
    }

    private fun onEvent(event: SplashEvent) {
        viewModelScope.launch { _event.emit(event) }
    }
}