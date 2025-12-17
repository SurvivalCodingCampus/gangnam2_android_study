package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SavedRecipesRoot(
    modifier: Modifier = Modifier,
    viewModel: SavedRecipesViewModel = hiltViewModel(),
    onItemClick: (Long) -> Unit = {},
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    SavedRecipesScreen(
        uiState = state,
        onClick = onItemClick,
    )
}

@Preview(showBackground = true)
@Composable
fun SavedRecipesRootPreview() {
    SavedRecipesScreen()
}
