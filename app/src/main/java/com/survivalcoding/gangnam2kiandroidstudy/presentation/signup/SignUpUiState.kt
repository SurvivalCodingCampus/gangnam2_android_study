package com.survivalcoding.gangnam2kiandroidstudy.presentation.signup

data class SignUpUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isTermsChecked: Boolean = false,
    val isLoading: Boolean = false
)