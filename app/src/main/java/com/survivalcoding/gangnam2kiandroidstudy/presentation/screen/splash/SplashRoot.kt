package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SplashRoot(
    modifier: Modifier = Modifier,
    onNavigateToSignIn: () -> Unit
) {
    SplashScreen(
        modifier = modifier,
        onStartClick = onNavigateToSignIn
    )
}