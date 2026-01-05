package com.survivalcoding.gangnam2kiandroidstudy.presentation.signup

sealed interface SignUpEvent {
    object NavigateToMain : SignUpEvent
    object NavigateToSignIn : SignUpEvent
    object GoogleSignInClick : SignUpEvent
    data class ShowMessage(val message: String) : SignUpEvent
}