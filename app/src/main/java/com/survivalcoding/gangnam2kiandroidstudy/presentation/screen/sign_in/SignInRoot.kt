package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_in

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors

@Composable
fun SignInRoot(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = viewModel(),
    onNavigateToHome: () -> Unit = {},
    onNavigateToSignUp: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val navigateToHome by viewModel.navigateToHome.collectAsStateWithLifecycle()

    LaunchedEffect(navigateToHome) {
        if (navigateToHome) {
            onNavigateToHome()
            viewModel.onNavigationHandled()
        }
    }

    Scaffold(
        containerColor = AppColors.white
    ) { innerPadding ->
        SignInScreen(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 30.dp),
            state = state,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onSignInClick = viewModel::onSignInClick,
            onSignUpClick = onNavigateToSignUp
        )
    }
}