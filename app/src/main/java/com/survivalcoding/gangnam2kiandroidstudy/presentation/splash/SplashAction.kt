package com.survivalcoding.gangnam2kiandroidstudy.presentation.splash

sealed interface SplashAction {
    data object OnStartClick: SplashAction
}