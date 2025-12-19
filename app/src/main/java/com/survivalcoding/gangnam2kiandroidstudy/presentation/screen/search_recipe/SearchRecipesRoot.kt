package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.FilterSearchBottomSheet
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRecipeRoot(
    onBack: () -> Unit,
    viewModel: SearchRecipeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    var isBottomSheetOpen by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(Unit) {
        viewModel.onAction(SearchRecipeAction.Load)
    }

    SearchRecipeScreen(
        state = state,
        onAction = { action ->
            when (action) {
                SearchRecipeAction.ClickBack -> {
                    onBack()
                }

                SearchRecipeAction.ClickFilter -> {
                    isBottomSheetOpen = true
                }

                else -> {
                    viewModel.onAction(action)
                }
            }
        }
    )

    if (isBottomSheetOpen) {
        FilterSearchBottomSheet(
            sheetState = sheetState,
            state = state.filterState,
            onChange = viewModel::updateFilterSearchState,
            onDismiss = { isBottomSheetOpen = false },
            onApplyFilter = {
                viewModel.onAction(SearchRecipeAction.ApplyFilter)
                isBottomSheetOpen = false
            }
        )
    }
}
