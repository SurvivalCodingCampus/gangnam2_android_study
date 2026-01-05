package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up

sealed interface SignUpEvent {
    data object NavigateToHome : SignUpEvent
    data object NavigateToSignIn : SignUpEvent
    data object RequestGoogleSignIn : SignUpEvent
    data class ShowError(val message: String) : SignUpEvent
}
