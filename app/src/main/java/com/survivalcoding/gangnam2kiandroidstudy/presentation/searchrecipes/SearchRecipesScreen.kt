package com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchBottomSheet
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchState
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField

@Composable
fun SearchRecipesScreen(
    modifier: Modifier = Modifier,
    state: SearchRecipesState,
    onSearchRecipes: (String) -> Unit = {},
    onTapFilterButton: () -> Unit = {},
    onUpdateFilterSearchState: (FilterSearchState) -> Unit = {},
) {
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
                onSearchRecipes(query)
            }
            ElevatedButton(onClick = {
                onTapFilterButton()
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
                onUpdateFilterSearchState(it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchRecipesScreenPreview() {
//    SearchRecipesScreen()
}