package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.network.NetworkMonitor
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private var _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()

    private var _event = MutableSharedFlow<Boolean>()
    val event = _event.asSharedFlow()


    init {
        viewModelScope.launch {
            networkMonitor.isConnectedNetwork.collect {
                _state.value = _state.value.copy(isNextButtonEnable = it)
                _event.emit(it)
            }
        }
    }
}