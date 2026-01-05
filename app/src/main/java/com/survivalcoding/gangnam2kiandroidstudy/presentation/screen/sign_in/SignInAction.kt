package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_in

sealed interface SignInAction {
    data object OnSignInClick : SignInAction
    data object OnForgotPasswordClick : SignInAction
    data object OnGoogleSignInClick : SignInAction
    data object OnFacebookSignInClick : SignInAction
    data object OnSignUpNavigateClick : SignInAction
    data class OnEmailChange(val email: String) : SignInAction
    data class OnPasswordChange(val password: String) : SignInAction
}