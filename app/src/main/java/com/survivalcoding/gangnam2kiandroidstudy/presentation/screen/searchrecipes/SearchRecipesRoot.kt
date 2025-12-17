package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchBottomSheet
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchRecipesRoot(
    viewModel: SearchRecipesViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchRecipesScreen(
        uiState = uiState,
        onSearchTextChange = viewModel::changeSearchText,
        onFilterClick = viewModel::showBottomSheet,
    )

    FilterSearchBottomSheet(
        isSheetVisible = uiState.isSheetVisible,
        searchFilter = uiState.searchFilter,
        onDismissRequest = viewModel::hideBottomSheet,
        onFilterChange = viewModel::changeSearchFilter,
        onFilter = viewModel::applyFilter,
    )
}