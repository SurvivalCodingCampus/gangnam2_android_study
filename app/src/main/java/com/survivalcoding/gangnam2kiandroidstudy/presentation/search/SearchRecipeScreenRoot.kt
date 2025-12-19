package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchBottomSheet
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRecipeScreenRoot(
    viewModel: SearchRecipesViewModel = koinViewModel(),
    onRecipeClick: (Int) -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SearchRecipeScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is SearchRecipesAction.SelectRecipes -> {
                    onRecipeClick(action.id)
                }
                else -> viewModel.onAction(action)
            }
        }
    )

    if (state.showBottomSheet) {
        FilterSearchBottomSheet(
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            currentFilterState = state.filterState,
            onDismiss = {
                viewModel.onAction(SearchRecipesAction.OnTapFilterButton)
            },
            onApplyFilter = {
                viewModel.onAction(SearchRecipesAction.OnUpdateFilterSearchState(it))
                viewModel.onAction(SearchRecipesAction.OnTapFilterButton)
            }
        )
    }
}