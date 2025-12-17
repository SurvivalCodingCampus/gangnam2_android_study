package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoot(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
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
