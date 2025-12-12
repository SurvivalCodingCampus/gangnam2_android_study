package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel : ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state

    private val _navigateToHome = MutableStateFlow(false)
    val navigateToHome: StateFlow<Boolean> = _navigateToHome

    fun onNameChange(value: String) {
        _state.update { current ->
            val updated = current.copy(name = value)
            updated.copy(isSignUpEnabled = updated.isValid())
        }
    }

    fun onEmailChange(email: String) {
        _state.update { current ->
            val updated = current.copy(email = email)
            updated.copy(isSignUpEnabled = updated.isValid())
        }
    }

    fun onPasswordChange(value: String) {
        _state.update { current ->
            val updated = current.copy(password = value)
            updated.copy(isSignUpEnabled = updated.isValid())
        }
    }

    fun onConfirmPasswordChange(value: String) {
        _state.update { current ->
            val updated = current.copy(confirmPassword = value)
            updated.copy(isSignUpEnabled = updated.isValid())
        }
    }

    fun onTermsCheckedChange(checked: Boolean) {
        _state.update { current ->
            val updated = current.copy(isTermsChecked = checked)
            updated.copy(isSignUpEnabled = updated.isValid())
        }
    }

    fun onSignUpClick() {
        if (_state.value.isSignUpEnabled) {
            _navigateToHome.value = true
        }
    }

    fun onNavigationHandled() {
        _navigateToHome.value = false
    }

    private fun SignUpState.isValid(): Boolean {
        return name.isNotBlank() &&
                email.isNotBlank() &&
                password.isNotBlank() &&
                password == confirmPassword &&
                isTermsChecked
    }
}
