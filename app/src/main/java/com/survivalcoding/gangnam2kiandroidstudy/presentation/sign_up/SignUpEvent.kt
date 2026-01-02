package com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_up

sealed interface SignUpEvent {
    data object NavigateToHome : SignUpEvent
    data object LaunchGoogleSignIn: SignUpEvent
    data class ShowError(val message: String) : SignUpEvent
}