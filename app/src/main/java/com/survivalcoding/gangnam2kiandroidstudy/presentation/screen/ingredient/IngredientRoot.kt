package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun IngredientRoot(
    recipeId: Long,
    onBackClick: () -> Unit,
    viewModel: IngredientViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var isMenuExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(recipeId) {
        viewModel.loadRecipeDetail(recipeId)
        viewModel.loadProcedure(recipeId)
    }

    IngredientScreen(
        state = state,
        isMenuExpanded = isMenuExpanded,
        onBackClick = onBackClick,
        onMoreClick = { isMenuExpanded = true },
        onDismissMoreMenu = { isMenuExpanded = false },
        onTapClick = { index -> viewModel.updateTabIndex(index) }
    )
}