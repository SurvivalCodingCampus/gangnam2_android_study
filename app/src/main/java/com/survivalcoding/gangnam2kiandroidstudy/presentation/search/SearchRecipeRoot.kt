package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SearchRecipesRoot(
    viewModel: SearchRecipesViewModel = hiltViewModel()
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