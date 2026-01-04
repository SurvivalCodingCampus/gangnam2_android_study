package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up

sealed interface SignUpAction {
    data class OnNameChange(val name: String) : SignUpAction
    data class OnEmailChange(val email: String) : SignUpAction
    data class OnPasswordChange(val password: String) : SignUpAction
    data class OnConfirmPasswordChange(val confirmPassword: String) : SignUpAction
    data class OnCheckedChange(val isChecked: Boolean) : SignUpAction
    data object OnSignUpClick : SignUpAction
    data object OnSignInNavigateClick : SignUpAction
    data object OnGoogleSignInClick : SignUpAction
    data object OnFacebookSignInClick : SignUpAction
}