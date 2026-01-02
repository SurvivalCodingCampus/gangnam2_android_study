package com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_up

data class SignUpState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val checkPassword: String = "",
    val isChecked: Boolean = false
)
