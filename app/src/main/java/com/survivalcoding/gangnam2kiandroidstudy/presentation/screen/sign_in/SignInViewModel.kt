package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
            SignInAction.OnSignInClick -> signIn()
            SignInAction.OnSignUpNavigateClick -> viewModelScope.launch {
                _event.emit(SignInEvent.NavigateToSignUp)
            }
            SignInAction.OnForgotPasswordClick -> {/*TODO*/}
            SignInAction.OnFacebookSignInClick -> {/*TODO*/}
            SignInAction.OnGoogleSignInClick -> {} // Handled in Root/UI
            is SignInAction.OnEmailChange -> _state.update { it.copy(email = action.email) }
            is SignInAction.OnPasswordChange -> _state.update { it.copy(password = action.password) }
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            _state.update { it.copy(error = null) }
            val result = authRepository.signInWithEmail(state.value.email, state.value.password)

            when (result) {
                is Result.Success -> _event.emit(SignInEvent.SignInSuccess)
                is Result.Error -> _state.update { it.copy(error = result.error.toString()) }
            }
        }
    }

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            _state.update { it.copy(error = null) }
            val result = authRepository.signInWithGoogle(idToken)

            when (result) {
                is Result.Success -> _event.emit(SignInEvent.SignInSuccess)
                is Result.Error -> _state.update { it.copy(error = result.error.toString()) }
            }
        }
    }
}


