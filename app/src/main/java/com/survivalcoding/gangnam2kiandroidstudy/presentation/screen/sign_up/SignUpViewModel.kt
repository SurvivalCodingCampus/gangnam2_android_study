package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up

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

class SignUpViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SignUpEvent>()
    val event = _event.asSharedFlow()

    fun onAction(action: SignUpAction) {
        when (action) {
            SignUpAction.OnSignInNavigateClick -> {
                viewModelScope.launch {
                    _event.emit(SignUpEvent.NavigateToSignIn)
                }
            }
            SignUpAction.OnSignUpClick -> signUp()
            SignUpAction.OnFacebookSignInClick -> {/*TODO*/}
            SignUpAction.OnGoogleSignInClick -> {}
            is SignUpAction.OnCheckedChange -> _state.update { it.copy(isChecked = action.isChecked) }
            is SignUpAction.OnConfirmPasswordChange -> _state.update { it.copy(confirmPassword = action.confirmPassword) }
            is SignUpAction.OnEmailChange -> _state.update { it.copy(email = action.email) }
            is SignUpAction.OnNameChange -> _state.update { it.copy(name = action.name) }
            is SignUpAction.OnPasswordChange -> _state.update { it.copy(password = action.password) }
        }
    }

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            _state.update { it.copy(error = null) }
            val result = authRepository.signInWithGoogle(idToken)

            when (result) {
                is Result.Success -> _event.emit(SignUpEvent.SignUpSuccess)
                is Result.Error -> _state.update { it.copy(error = result.error.toString()) }
            }
        }
    }

    private fun signUp() {
        val state = state.value
        if (!state.isChecked) {
            _state.update { it.copy(error = "Please accept terms and conditions") }
            return
        }
        if (state.password != state.confirmPassword) {
            _state.update { it.copy(error = "Passwords do not match") }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(error = null) }
            val result = authRepository.signUpWithEmail(
                name = state.name,
                email = state.email,
                password = state.password,
            )

            when (result) {
                is Result.Success -> {
                    _event.emit(SignUpEvent.SignUpSuccess)
                }

                is Result.Error -> _state.update { it.copy(error = result.error.toString()) }
            }
        }
    }
}


