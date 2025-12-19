package com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchRecipesRoot(
    navigateBack: () -> Unit,
    viewModel: SearchRecipesViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    SearchRecipesScreen(
        state = state,
        onAction = { action ->
            viewModel.onAction(
                action = action,
                navigateBack = navigateBack,
            )
        },
    )
}
