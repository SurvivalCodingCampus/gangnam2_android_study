package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.FilterSearchBottomSheet

@Composable
fun SearchRecipesRoot(
    viewModel: SearchRecipesViewModel = viewModel(
        factory = SearchRecipesViewModel.Factory
    )
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SearchRecipesScreen(
        state = state,
        onKeywordChange = viewModel::updateSearchKeyword,
        onFilterClick = { viewModel.showBottomSheet(true) },
    )

    // filter bottom sheet
    if (state.showBottomSheet) {
        FilterSearchBottomSheet(
            initialState = state.filterState,
            onDismiss = { viewModel.showBottomSheet(false) },
            onApplyFilter = {
                viewModel.applyFilters(it)
                viewModel.showBottomSheet(false)
            }
        )
    }

}
