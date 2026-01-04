package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up

sealed interface SignUpAction {

    data class NameChanged(val value: String) : SignUpAction
    data class EmailChanged(val value: String) : SignUpAction
    data class PasswordChanged(val value: String) : SignUpAction
    data class ConfirmPasswordChanged(val value: String) : SignUpAction

    data class TermsChecked(val checked: Boolean) : SignUpAction

    data object ClickSignUp : SignUpAction
    data object ClickNavigateToSignIn : SignUpAction
}
