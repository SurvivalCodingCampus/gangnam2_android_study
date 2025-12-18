package com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchBottomSheet
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField

@Composable
fun SearchRecipesScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchRecipesViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()


    Column(
        modifier = modifier
            .safeContentPadding()
            .fillMaxSize(),
    ) {
        Row {
            InputField(
                modifier = Modifier.weight(1f),
                value = state.searchKeyword,
            ) { query ->
                viewModel.searchRecipes(query)
            }
            ElevatedButton(onClick = {
                viewModel.tapFilterButton()
            }) {
                Text("필터")
            }
        }

        Box {
            LazyColumn() {
                items(state.filteredRecipes.size) {
                    Text(state.filteredRecipes[it].title)
                }
            }
        }

        if (state.showBottomSheet) {
            FilterSearchBottomSheet(
                state = state.filterSearchState,
            ) {
                viewModel.updateFilterSearchState(it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchRecipesScreenPreview() {
    SearchRecipesScreen()
}