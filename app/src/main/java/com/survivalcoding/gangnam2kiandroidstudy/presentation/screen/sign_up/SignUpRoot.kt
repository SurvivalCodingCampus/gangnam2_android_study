package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_in.SignUpScreen
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors


@Composable
fun SignUpRoot(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = viewModel(),
    onNavigateToHome: () -> Unit = {},
    onNavigateToSignIn: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val navigateToHome by viewModel.navigateToHome.collectAsStateWithLifecycle()

    if (navigateToHome) {
        onNavigateToHome()
        viewModel.onNavigationHandled()
    }

    Scaffold(containerColor = AppColors.white) { innerPadding ->
        SignUpScreen(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 30.dp),
            state = state,
            onNameChange = viewModel::onNameChange,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
            onTermsCheckedChange = viewModel::onTermsCheckedChange,
            onSignUpClick = viewModel::onSignUpClick,
            onNavigateToSignIn = onNavigateToSignIn
        )
    }
}
