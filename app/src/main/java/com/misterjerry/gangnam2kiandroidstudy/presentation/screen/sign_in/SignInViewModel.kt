package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.sign_in

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.AuthRepository
import com.misterjerry.gangnam2kiandroidstudy.presentation.screen.sign_up.GoogleLoginClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private var _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun signInWithEmail() {
        viewModelScope.launch {
            val email = state.value.email
            val password = state.value.password
            if (email.isNotBlank() && password.isNotBlank()) {
                authRepository.signInWithEmail(email, password)
                    .onSuccess {
                        _state.value = _state.value.copy(isLoginSuccess = true)
                    }
                    .onFailure { e ->
                        android.util.Log.e("SignInViewModel", "Email Sign in failed", e)
                        // Handle error state if needed
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
                    android.util.Log.e("SignInViewModel", "Firebase Sign in failed", e)
                    _state.value = _state.value.copy(isLoginSuccess = false)
                }
            }.onFailure { e ->
                android.util.Log.e("SignInViewModel", "Google ID Token retrieval failed", e)
                _state.value = _state.value.copy(isLoginSuccess = false)
            }
        }
    }
}