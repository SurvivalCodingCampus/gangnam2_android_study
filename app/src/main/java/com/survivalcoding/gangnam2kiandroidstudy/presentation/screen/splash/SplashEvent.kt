package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

sealed interface SplashEvent {
    object ShowNetworkDisconnected : SplashEvent
}
