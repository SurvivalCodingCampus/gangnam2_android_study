package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.SignUpWithEmailUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.SignUpWithGoogleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase,
    private val signUpWithGoogleUseCase: SignUpWithGoogleUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state

    private val _event = MutableSharedFlow<SignUpEvent>()
    val event = _event

    fun onAction(action: SignUpAction) {
        when (action) {
            is SignUpAction.NameChanged ->
                updateState { it.copy(name = action.value) }

            is SignUpAction.EmailChanged ->
                updateState { it.copy(email = action.value) }

            is SignUpAction.PasswordChanged ->
                updateState { it.copy(password = action.value) }

            is SignUpAction.ConfirmPasswordChanged ->
                updateState { it.copy(confirmPassword = action.value) }

            is SignUpAction.TermsChecked ->
                updateState { it.copy(isTermsChecked = action.checked) }

            SignUpAction.ClickSignUp -> {
                if (_state.value.isValid()) {
                    viewModelScope.launch {
                        val result = signUpWithEmailUseCase(
                            name = _state.value.name,
                            email = _state.value.email,
                            password = _state.value.password
                        )
                        result.onSuccess {
                            emitEvent(SignUpEvent.NavigateToHome)
                        }.onFailure {
                            emitEvent(SignUpEvent.ShowError(it.message ?: "Sign up failed"))
                        }
                    }
                }
            }

            SignUpAction.ClickGoogleSignUp -> {
                emitEvent(SignUpEvent.RequestGoogleSignIn)
            }

            is SignUpAction.GoogleSignInResult -> {
                viewModelScope.launch {
                    val result = signUpWithGoogleUseCase(action.idToken)
                    result.onSuccess {
                        emitEvent(SignUpEvent.NavigateToHome)
                    }.onFailure {
                        emitEvent(SignUpEvent.ShowError(it.message ?: "Google sign in failed"))
                    }
                }
            }

            SignUpAction.ClickNavigateToSignIn -> {
                emitEvent(SignUpEvent.NavigateToSignIn)
            }
        }
    }

    private fun updateState(reducer: (SignUpState) -> SignUpState) {
        _state.update { current ->
            val updated = reducer(current)
            updated.copy(isSignUpEnabled = updated.isValid())
        }
    }

    private fun emitEvent(event: SignUpEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    private fun SignUpState.isValid(): Boolean =
        name.isNotBlank() &&
                email.isNotBlank() &&
                password.isNotBlank() &&
                password == confirmPassword &&
                isTermsChecked
}
