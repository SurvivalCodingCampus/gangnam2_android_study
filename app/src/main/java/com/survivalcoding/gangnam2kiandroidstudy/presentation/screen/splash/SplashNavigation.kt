package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

sealed interface SplashNavigation {
    data object SignIn : SplashNavigation
}