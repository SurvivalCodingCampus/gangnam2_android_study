package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.sign_up

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpRoot(
    viewmodel: SignUpViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit,
    onSignInButtonClicked: () -> Unit
) {
    val context = LocalContext.current
    val state by viewmodel.state.collectAsState()

    LaunchedEffect(state.isLoginSuccess) {
        if (state.isLoginSuccess) {
            onLoginSuccess()
        }
    }

    SignUpScreen(
        state = state,
        onNameChange = viewmodel::onNameChange,
        onEmailChange = viewmodel::onEmailChange,
        onPasswordChange = viewmodel::onPasswordChange,
        onConfirmPasswordChange = viewmodel::onConfirmPasswordChange,
        onSignUpButtonClick = viewmodel::signUpWithEmail,
        onSignInButtonClick = { onSignInButtonClicked() },
        onGoogleButtonClick = { viewmodel.signInWithGoogle(context) })
}