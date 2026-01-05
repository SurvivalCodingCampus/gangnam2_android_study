package com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_up.SignUpEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SignInEvent>()
    val event = _event.asSharedFlow()


    fun onAction(action: SignInAction) {
        when (action) {
            is SignInAction.OnEmailChange -> {
                _state.value = _state.value.copy(email = action.email)
            }

            is SignInAction.OnPasswordChange -> {
                _state.value = _state.value.copy(password = action.password)
            }

            SignInAction.OnSignInClick -> {
                signIn()
            }

            SignInAction.OnSignUpClick -> {
                viewModelScope.launch {
                    _event.emit(SignInEvent.NavigateToSignUp)
                }
            }
            SignInAction.OnGoogleClick -> {
                viewModelScope.launch {
                    _event.emit(SignInEvent.LaunchGoogleSignIn)
                }
            }
            else -> {

            }
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            try {
                authRepository.signInWithEmail(
                    email = state.value.email,
                    password = state.value.password
                )
                _event.emit(SignInEvent.NavigateToHome)
            } catch (e: Exception) {
                _event.emit(SignInEvent.ShowError(e.message ?: "Unknown Error"))
            }
        }
    }

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            try {
                authRepository.signInWithGoogle(idToken)
                _event.emit(SignInEvent.NavigateToHome)
            } catch (e: Exception) {
                _event.emit(SignInEvent.ShowError("Google signup failed"))
            }
        }
    }

    fun onGoogleIdTokenReceivedError(message: String) {
        viewModelScope.launch {
            _event.emit(SignInEvent.ShowError(message))
        }
    }


}