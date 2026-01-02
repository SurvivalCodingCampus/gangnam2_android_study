package com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_up

sealed interface SignUpAction {
    data class OnNameChange(val name: String) : SignUpAction
    data class OnEmailChange(val email: String) : SignUpAction
    data class OnPasswordChange(val password: String) : SignUpAction
    data class OnCheckPasswordChange(val checkPassword: String) : SignUpAction
    data object OnToggleCheck : SignUpAction
    data object OnSignUpClick : SignUpAction
    data object OnSignInClick : SignUpAction
    data object OnGoogleClick : SignUpAction
    data object OnFacebookClick : SignUpAction
}
