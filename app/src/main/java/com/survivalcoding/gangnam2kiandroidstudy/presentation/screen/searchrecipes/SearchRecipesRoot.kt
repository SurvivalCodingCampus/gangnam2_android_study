package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchBottomSheet
import kotlinx.coroutines.FlowPreview

@ExperimentalMaterial3Api
@FlowPreview
@Composable
fun SearchRecipesRoot(
    viewModel: SearchRecipesViewModel = viewModel(factory = SearchRecipesViewModel.Factory),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchRecipesScreen(
        uiState = uiState,
        onSearchTextChange = viewModel::changeSearchText,
        onSettingsClick = viewModel::showBottomSheet,
    )

    FilterSearchBottomSheet(
        isSheetVisible = uiState.isSheetVisible,
        searchFilter = uiState.searchFilter,
        onDismissRequest = viewModel::hideBottomSheet,
        onFilterChange = viewModel::changeSearchFilter,
        onFilter = viewModel::applyFilter,
    )
}