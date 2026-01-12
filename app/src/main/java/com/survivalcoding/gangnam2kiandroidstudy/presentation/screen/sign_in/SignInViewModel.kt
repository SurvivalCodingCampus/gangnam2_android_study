package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.BuildConfig
import com.survivalcoding.gangnam2kiandroidstudy.auth.AuthStateHolder
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
            is SignInAction.OnEmailChange -> {
                _state.update { it.copy(email = action.email) }
            }

            is SignInAction.OnPasswordChange -> {
                _state.update { it.copy(password = action.password) }
            }

            SignInAction.OnSignInClick -> {
                signInWithEmail()
            }

            SignInAction.OnSignUpClick -> {
                emitEvent(SignInEvent.NavigateToSignUp)
            }

            SignInAction.OnGoogleClick -> {
                emitEvent(SignInEvent.LaunchGoogleSignIn)
            }
        }
    }

    /**
     * 이메일 / 비밀번호 로그인
     *
     * Firebase Auth 연동 전 임시 로그인 로직 포함
     * 테스트 계정:
     *   email: test@test.com
     *   password: 1234
     * DEBUG 빌드에서만 허용
     */
    private fun signInWithEmail() {
        val email = state.value.email.trim()
        val password = state.value.password

        // 사전 검증
        if (email.isEmpty() || password.isEmpty()) {
            _state.update {
                it.copy(error = "이메일과 비밀번호를 입력해주세요.")
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            /* ---------------- 임시 로그인 분기 ---------------- */
            if (BuildConfig.DEBUG && email == "test@test.com" && password == "1234") {
                _state.update { it.copy(isLoading = false) }

                AuthStateHolder.forceAuthenticatedForDebug()

                return@launch
            }
            /* -------------------------------------------------- */

            // 실제 Firebase 로그인 (현재는 실패해도 무방)
            val result = authRepository.signInWithEmail(email, password)

            result
                .onSuccess {
                    _event.emit(SignInEvent.NavigateToHome)
                }
                .onFailure { throwable ->
                    _state.update { state ->
                        state.copy(
                            isLoading = false,
                            error = throwable.message ?: "로그인에 실패했습니다."
                        )
                    }
                }
        }
    }

    /**
     * Google 로그인 (CredentialManager → idToken)
     * 현재 Firebase 문제로 인해 실제 사용은 보류 가능
     */
    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            val result = authRepository.signInWithGoogle(idToken)

            result
                .onSuccess {
                    _event.emit(SignInEvent.NavigateToHome)
                }
                .onFailure { throwable ->
                    _event.emit(
                        SignInEvent.ShowError(
                            throwable.message ?: "Google 로그인에 실패했습니다."
                        )
                    )
                }

            _state.update { it.copy(isLoading = false) }
        }
    }

    fun onGoogleIdTokenReceivedError(message: String) {
        emitEvent(SignInEvent.ShowError(message))
    }

    private fun emitEvent(event: SignInEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}
