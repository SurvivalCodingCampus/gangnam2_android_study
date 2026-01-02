package com.survivalcoding.gangnam2kiandroidstudy.presentation.signin

data class SignInUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false
)