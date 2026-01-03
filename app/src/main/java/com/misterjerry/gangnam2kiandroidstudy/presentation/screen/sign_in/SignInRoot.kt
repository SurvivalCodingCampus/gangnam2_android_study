package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.sign_in

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInRoot(
    onSignUpButtonClick: () -> Unit,
    onSignInButtonClick: () -> Unit,
    viewmodel: SignInViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit
) {
    val context = LocalContext.current
    val state by viewmodel.state.collectAsState()

    LaunchedEffect(state.isLoginSuccess) {
        if (state.isLoginSuccess) {
            onLoginSuccess()
        }
    }

    SignInScreen(
        state = state,
        onEmailChange = viewmodel::onEmailChange,
        onPasswordChange = viewmodel::onPasswordChange,
        onSignUpButtonClick = onSignUpButtonClick,
        onSignInButtonClick = viewmodel::signInWithEmail,
        onGoogleButtonClick = { viewmodel.signInWithGoogle(context) }
    )
}