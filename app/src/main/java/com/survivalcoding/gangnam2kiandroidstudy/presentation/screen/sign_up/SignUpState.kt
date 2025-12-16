package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up

data class SignUpState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isTermsChecked: Boolean = false,
    val isSignUpEnabled: Boolean = false
)