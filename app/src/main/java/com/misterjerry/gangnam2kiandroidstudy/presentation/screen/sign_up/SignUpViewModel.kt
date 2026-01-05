package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.sign_up

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private var _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    fun onNameChange(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _state.value = _state.value.copy(confirmPassword = confirmPassword)
    }

    fun signUpWithEmail() {
        viewModelScope.launch {
            val email = state.value.email
            val password = state.value.password
            val confirmPassword = state.value.confirmPassword

            if (email.isNotBlank() && password.isNotBlank() && password == confirmPassword) {
                authRepository.signUpWithEmail(email, password)
                    .onSuccess {
                        _state.value = _state.value.copy(isLoginSuccess = true)
                    }
                    .onFailure { e ->
                        android.util.Log.e("SignUpViewModel", "Email Sign Up failed", e)
                        // Handle error
                    }
            }
        }
    }


    fun signInWithGoogle(context: Context) {
        viewModelScope.launch {
            val googleLoginClient = GoogleLoginClient(context)
            val tokenResult = googleLoginClient.getGoogleIdToken()

            tokenResult.onSuccess { idToken ->
                val result = authRepository.signInWithGoogle(idToken)
                result.onSuccess {
                    _state.value = _state.value.copy(isLoginSuccess = true)
                }.onFailure { e ->
                    android.util.Log.e("SignUpViewModel", "Firebase Sign in failed", e)
                    _state.value = _state.value.copy(isLoginSuccess = false)
                }
            }.onFailure { e ->
                android.util.Log.e("SignUpViewModel", "Google ID Token retrieval failed", e)
                _state.value = _state.value.copy(isLoginSuccess = false)
            }
        }
    }
}