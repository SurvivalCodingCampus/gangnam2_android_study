package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors

@Composable
fun SavedRecipesRoot(
    modifier: Modifier = Modifier,
    viewModel: SavedRecipesViewModel = hiltViewModel(),
    onNavigateToRecipeDetail: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = AppColors.white
    ) { innerPadding ->
        SavedRecipesScreen(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 30.dp),
            state = state,
            onBookmarkClick = viewModel::onBookmarkClick,
            onRecipeClick = onNavigateToRecipeDetail
        )
    }
}
