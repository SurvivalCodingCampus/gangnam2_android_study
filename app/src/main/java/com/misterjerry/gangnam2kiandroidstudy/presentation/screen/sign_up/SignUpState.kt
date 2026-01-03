package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.sign_up

data class SignUpState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoginSuccess: Boolean = false
)