package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_in

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state

    private val _navigateToHome = MutableStateFlow(false)
    val navigateToHome: StateFlow<Boolean> = _navigateToHome

    fun onEmailChange(email: String) {
        _state.update { current ->
            val updated = current.copy(email = email)
            updated.copy(isSignInEnabled = updated.isValid())
        }
    }

    fun onPasswordChange(password: String) {
        _state.update { current ->
            val updated = current.copy(password = password)
            updated.copy(isSignInEnabled = updated.isValid())
        }
    }

    fun onSignInClick() {
        if (_state.value.isSignInEnabled) {
            _navigateToHome.value = true
        }
    }

    fun onNavigationHandled() {
        _navigateToHome.value = false
    }

    private fun SignInState.isValid(): Boolean {
        return email.isNotBlank() &&
                password.isNotBlank()
    }
}
