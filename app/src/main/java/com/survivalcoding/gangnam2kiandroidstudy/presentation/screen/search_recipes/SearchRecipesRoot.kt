package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.FilterSearchBottomSheet

@Composable
fun SearchRecipesRoot(
    modifier: Modifier = Modifier,
    application: AppApplication =
        LocalContext.current.applicationContext as AppApplication,
    onBack: () -> Unit,

    ) {

    val viewModel: SearchRecipesViewModel = viewModel(
        factory = SearchRecipesViewModel.factory(application)
    )
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold { innerpadding ->
        SearchRecipesScreen(
            modifier = modifier
                .fillMaxSize()
                .padding(innerpadding)
                .padding(horizontal = 30.dp),
            state = state,
            onKeywordChange = viewModel::updateSearchKeyword,
            onFilterClick = { viewModel.showBottomSheet(true) },
            onBackClick = onBack
        )
    }

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
