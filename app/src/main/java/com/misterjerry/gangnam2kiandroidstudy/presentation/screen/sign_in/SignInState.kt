package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.sign_in

data class SignInState(
    val email: String = "",
    val password: String = "",
    val isLoginSuccess: Boolean = false
)