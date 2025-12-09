package com.survivalcoding.gangnam2kiandroidstudy.presentation.util

object AuthValidator {

    fun validateName(name: String): Boolean =
        name.length >= 2

    fun validateEmail(email: String): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun validatePassword(password: String): Boolean =
        password.length >= 8 && password.any { it.isDigit() }

    fun validateConfirmPassword(password: String, confirm: String): Boolean =
        password == confirm

    fun validateTerms(isChecked: Boolean): Boolean =
        isChecked
}