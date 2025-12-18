package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchBottomSheet
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchRecipesRoot(
    navigateToBack: () -> Unit,
    navigateToRecipeDetail: (Long) -> Unit,
    viewModel: SearchRecipesViewModel = koinViewModel(),
) {
    // UI 상태
    val state by viewModel.state.collectAsStateWithLifecycle()

    SearchRecipesScreen(
        state = state,
        onAction = {action ->
            viewModel.onAction(
                action = action,
                navigateToBack = navigateToBack,
                navigateToRecipeDetail = navigateToRecipeDetail,
            )
        },
    )
}