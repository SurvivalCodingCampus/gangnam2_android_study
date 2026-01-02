package com.survivalcoding.gangnam2kiandroidstudy.presentation.signin

sealed interface SignInEvent {
    object NavigateToMain : SignInEvent
    object NavigateToForgotPassword : SignInEvent
    object NavigateToSignUp : SignInEvent
    object GoogleSignInClick : SignInEvent
    data class ShowMessage(val message: String) : SignInEvent
}