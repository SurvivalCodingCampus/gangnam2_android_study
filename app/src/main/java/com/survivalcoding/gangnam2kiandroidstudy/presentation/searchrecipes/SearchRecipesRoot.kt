package com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchRecipesRoot(
    viewModel: SearchRecipesViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    SearchRecipesScreen(
        state = state,
        onChangeSearchText = viewModel::changeSearchText,
    )

}