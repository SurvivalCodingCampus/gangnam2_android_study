package com.survivalcoding.gangnam2kiandroidstudy.presentation.signup

sealed interface SignUpAction {
    data class NameChanged(val name: String) : SignUpAction
    data class EmailChanged(val email: String) : SignUpAction
    data class PasswordChanged(val password: String) : SignUpAction
    data class ConfirmPasswordChanged(val confirmPassword: String) : SignUpAction
    data class SignUpClicked(val name: String, val email: String, val password: String) : SignUpAction
    data class TermsChecked(val checked: Boolean) : SignUpAction
    object GoogleSignInClicked : SignUpAction
    object FacebookSignInClicked : SignUpAction
    object SignInClicked : SignUpAction
    data class GoogleIdTokenReceive(val id: String) : SignUpAction
}