package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.di.DependencyContainer

@Composable
fun HomeRoot(viewModel: HomeViewModel = viewModel(
    factory = DependencyContainer.provideHomeViewModelFactory(LocalContext.current)
)) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(uiState, viewModel::onSearchKeywordChange, viewModel::onSelectCategory)
}