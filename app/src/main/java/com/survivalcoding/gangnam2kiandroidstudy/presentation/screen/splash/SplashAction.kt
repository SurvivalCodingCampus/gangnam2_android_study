package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

sealed interface SplashAction {
    data object ClickStratButton : SplashAction
}