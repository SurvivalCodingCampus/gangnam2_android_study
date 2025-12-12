package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication

@Composable
fun HomeRoot(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = HomeViewModel.factory(
            LocalContext.current.applicationContext as AppApplication,
        ),
    ),
    onDishClick: (Long) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        onQueryChange = viewModel::changeQuery,
        onCategorySelect = viewModel::selectCategory,
        onDishClick = onDishClick,
    )
}