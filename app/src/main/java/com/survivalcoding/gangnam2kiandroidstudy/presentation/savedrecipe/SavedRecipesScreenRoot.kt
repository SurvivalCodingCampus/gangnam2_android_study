package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedRecipesScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: SavedRecipesViewModel = koinViewModel(),
    onCardClick: (Int) -> Unit = {}
) {
    val state = viewModel.state.collectAsState()
    SavedRecipesScreen(
        modifier = modifier,
        state = state.value,
        onBookmarkClick = {
            viewModel.getRecipes(it)
        },
        onCardClick = onCardClick
    )
}