package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchRecipesRoot(
    viewModel: SearchRecipesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var showBottomSheet by remember { mutableStateOf(false) }

    SearchRecipeScreen(
        state = state,
        showBottomSheet = showBottomSheet,
        onSearchQuery = {
            viewModel.updateSearchQuery(it)
        },
        tapFilterButton = {
            showBottomSheet = it
        },
        onApplyFilter = {
            viewModel.applyFilters(it)
        },

    )
}