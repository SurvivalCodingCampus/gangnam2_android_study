package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication

@Composable
fun SavedRecipesRoot(
    modifier: Modifier = Modifier,
    viewModel: SavedRecipesViewModel = viewModel(
        factory = SavedRecipesViewModel.factory(
            LocalContext.current.applicationContext as AppApplication,
        ),
    ),
    onCardClick: (Long) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SavedRecipesScreen(
        modifier = modifier,
        uiState = uiState,
        onCardClick = onCardClick,
        onBookmarkClick = viewModel::toggleBookmark,
    )
}