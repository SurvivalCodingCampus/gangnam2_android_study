package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(

) : ViewModel() {
    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SplashEvent>()
    val event = _event.asSharedFlow()

    // 먼저 화면 진입 하자마자 네트워크 상태 가져오기
    init {

    }

    // Action
    fun onAction(action: SplashAction) {
        when (action) {
            SplashAction.ClickStratButton -> {
                onButtonClick()
            }
        }
    }

    // Event
    private fun onButtonClick() {
        viewModelScope.launch {
            _event.emit(
                SplashEvent.NavigateToSignIn
            )
        }
    }


}