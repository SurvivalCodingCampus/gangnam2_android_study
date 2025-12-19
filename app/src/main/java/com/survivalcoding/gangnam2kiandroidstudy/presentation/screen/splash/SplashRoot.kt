package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun SplashRoot(
    modifier: Modifier = Modifier,
    onNavigateToSignIn: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is SplashEvent.NavigateToSignIn -> {
                    onNavigateToSignIn()
                }

                is SplashEvent.ShowSnackBar -> {}
            }

        }
    }

    SplashScreen(
        modifier = modifier,
        onAction = viewModel::onAction
    )
}