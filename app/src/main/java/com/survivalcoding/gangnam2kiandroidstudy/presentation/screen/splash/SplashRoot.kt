package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SplashRoot(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel,
    onNavigateToSignIn: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SplashScreen(
        modifier = modifier,
        onAction = viewModel::onAction
    )
}