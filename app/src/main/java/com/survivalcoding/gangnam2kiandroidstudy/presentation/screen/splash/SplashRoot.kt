package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel


@Composable
fun SplashRoot(onStartButtonClick: () -> Unit, viewModel: SplashViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { it ->
        Box(modifier = Modifier.padding(it)) {
            SplashScreen(
                onStartButtonClick = {
                    onStartButtonClick()
                },
                isEnable = state.isNextButtonEnable
            )
        }
    }
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            Log.d("SplashRoot", "SplashRoot:$it")
            if (!it) {
                snackbarHostState.showSnackbar("네트워크가 연결 안됨.")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun SplashRootPreview() {
//    SplashRoot(onStartButtonClick = {
//
//    })

}