package com.survivalcoding.gangnam2kiandroidstudy.presentation.signin

sealed interface SignInAction {
    data class EmailChanged(val email: String) : SignInAction
    data class PasswordChanged(val password: String) : SignInAction
    data class SignInClicked(val email: String, val password: String) : SignInAction
    object SignUpClicked : SignInAction
    object ForgotPasswordClicked : SignInAction
    object GoogleSignInClicked : SignInAction
    object FacebookSignInClicked : SignInAction
    data class GoogleIdTokenReceive(val id: String) : SignInAction
}