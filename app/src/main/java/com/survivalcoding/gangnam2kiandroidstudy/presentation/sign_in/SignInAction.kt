package com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_in


sealed interface SignInAction {
    data class OnEmailChange(val email: String) : SignInAction
    data class OnPasswordChange(val password: String) : SignInAction
    data object OnSignUpClick : SignInAction
    data object OnSignInClick : SignInAction
    data object OnGoogleClick : SignInAction
    data object OnFacebookClick : SignInAction
}