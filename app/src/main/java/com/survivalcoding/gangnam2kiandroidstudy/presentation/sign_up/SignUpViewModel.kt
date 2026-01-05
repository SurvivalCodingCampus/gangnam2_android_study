package com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SignUpEvent>()
    val event = _event.asSharedFlow()

    fun onAction(action: SignUpAction) {
        when (action) {
            is SignUpAction.OnNameChange ->
                _state.update { it.copy(name = action.name) }

            is SignUpAction.OnEmailChange ->
                _state.update { it.copy(email = action.email) }

            is SignUpAction.OnPasswordChange ->
                _state.update { it.copy(password = action.password) }

            is SignUpAction.OnCheckPasswordChange ->
                _state.update { it.copy(checkPassword = action.checkPassword) }

            SignUpAction.OnToggleCheck ->
                _state.update { it.copy(isChecked = !it.isChecked) }

            SignUpAction.OnSignUpClick -> {
                signUp()
            }

            SignUpAction.OnGoogleClick -> {
                viewModelScope.launch {
                    _event.emit(SignUpEvent.LaunchGoogleSignIn)
                }
            }
            else -> Unit
        }

    }

    fun signUp() {
        viewModelScope.launch {
            try {
                authRepository.signUpWithEmail(_state.value.email, _state.value.password)

                _event.emit(SignUpEvent.NavigateToHome)
            } catch (e: Exception) {
                _event.emit(
                    SignUpEvent.ShowError(e.message ?: "Unknown Error")
                )
            }
        }
    }


    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            try {
                authRepository.signInWithGoogle(idToken)
                _event.emit(SignUpEvent.NavigateToHome)
            } catch (e: Exception) {
                _event.emit(SignUpEvent.ShowError("Google signup failed"))
            }
        }
    }

}