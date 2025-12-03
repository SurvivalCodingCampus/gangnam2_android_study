package com.survivalcoding.gangnam2kiandroidstudy.presentation.util

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun KeyboardVisibilityHandler(content: @Composable () -> Unit) {
    val imeInsets = WindowInsets.ime
    val density = LocalDensity.current
    val focusManager = LocalFocusManager.current

    // 키보드 높이가 0보다 크면 => 키보드가 보이는 상태
    val isKeyboardVisible by remember {
        derivedStateOf { imeInsets.getBottom(density) > 0 }
    }

    LaunchedEffect(isKeyboardVisible) {
        if (!isKeyboardVisible) {
            // 키보드 내려가면 자동으로 포커스 해제
            focusManager.clearFocus()
        }
    }

    content()
}
