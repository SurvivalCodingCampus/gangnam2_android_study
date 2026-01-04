package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_in

sealed interface SignInEvent {
    data object SignInSuccess : SignInEvent
    data object NavigateToSignUp : SignInEvent
}