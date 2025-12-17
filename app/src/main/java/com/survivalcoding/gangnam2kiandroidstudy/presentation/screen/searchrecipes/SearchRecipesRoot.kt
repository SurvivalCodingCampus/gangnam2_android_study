package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchBottomSheet

@Composable
fun SearchRecipesRoot(
    viewModel: SearchRecipesViewModel = viewModel(),
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