package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeRoot(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        onCategoryClick = viewModel::changeCategory,
        onSearchQueryChange = viewModel::changeSearchText,
    )
}

@Preview(showBackground = true)
@Composable
fun HomeRootPreview() {

    HomeScreen(
        state = HomeState(),
        onCategoryClick = {},
        onSearchQueryChange = {},
    )
}
