package com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_in


sealed interface SignInEvent {
    data object NavigateToHome : SignInEvent
    data object NavigateToSignUp : SignInEvent
    data object LaunchGoogleSignIn: SignInEvent
    data class ShowError(val message: String) : SignInEvent
}