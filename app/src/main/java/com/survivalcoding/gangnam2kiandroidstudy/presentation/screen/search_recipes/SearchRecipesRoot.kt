package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SearchRecipesRoot(viewModel: SearchRecipesViewModel = viewModel(factory = SearchRecipesViewModel.Factory)) {
    val searchRecipes by viewModel.state.collectAsState(SearchRecipesState())
    Log.d("SearchRecipesRoot", "searchRecipes: $searchRecipes")
    SearchRecipesScreen(
        state = searchRecipes,
        onViewmodelCalled = { searchText, time, rate, category, enableBottomSheet ->
            if (enableBottomSheet) {
                viewModel.toggleBottomSheet()
            }
            viewModel.filterRecipes(searchText, time, rate, category)

            Log.d(
                "SearchRecipesRoot",
                "searchText: $searchText, time: $time, rate: $rate, category: $category"
            )

        }
    )
}