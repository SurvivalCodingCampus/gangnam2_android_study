package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashRoot(
    onStartClick: () -> Unit,
    viewModel: SplashViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                SplashEvent.ShowNetworkDisconnected -> {
                    snackbarHostState.showSnackbar(
                        "네트워크가 연결되지 않았습니다"
                    )
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        SplashScreen(
            onStartClick = onStartClick,
            isConnected = state.isNetworkConnected,
            modifier = Modifier.padding(paddingValues)
        )
    }
}
