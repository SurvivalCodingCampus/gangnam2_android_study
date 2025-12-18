package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoot(viewModel: HomeViewModel = koinViewModel()) {
    val homeState by viewModel.state.collectAsState()
    HomeScreen(homeState) {
        viewModel.onSelectedCategory(it)
    }
}