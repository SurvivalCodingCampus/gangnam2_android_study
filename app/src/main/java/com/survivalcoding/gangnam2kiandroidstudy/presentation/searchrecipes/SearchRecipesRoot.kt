package com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun SearchRecipesRoot(
    viewModel: SearchRecipesViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    SearchRecipesScreen(
        state = state,
        onChangeSearchText = viewModel::changeSearchText,
    )

}