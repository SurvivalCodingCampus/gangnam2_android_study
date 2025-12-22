package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SplashRoot(
    modifier: Modifier = Modifier,
    onNavigateToSignIn: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    // 네트워크 상태 수집
    val isNetworkAvailable by viewModel.isNetworkAvailable.collectAsStateWithLifecycle()

    // 네비게이션 이벤트
    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                SplashEvent.NavigateToSignIn -> {
                    onNavigateToSignIn()
                }
            }
        }
    }

    SplashScreen(
        modifier = modifier,
        isNetworkAvailable = isNetworkAvailable,
        onAction = viewModel::onAction
    )
}