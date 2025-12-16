package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterBox
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchBottomSheet
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SearchInputField
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SearchRecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRecipeScreen(
    state: SearchRecipeState= SearchRecipeState(),
    showBottomSheet: Boolean = false,
    onSearchQuery: (String) -> Unit = {},
    tapFilterButton: (Boolean) -> Unit = {},
    onApplyFilter: (FilterSearchState) -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = AppColors.white
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Search recipes",
                style = AppTextStyles.mediumTextBold
            )
            Spacer(modifier = Modifier.height(17.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchInputField(
                    modifier = Modifier.weight(1f),
                    value = state.searchQuery,
                    placeholder = "Search Recipe",
                ) {
                    onSearchQuery(it)
                }
                Spacer(modifier = Modifier.width(20.dp))
                FilterBox {
                    tapFilterButton(true)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = state.searchText,
                    style = AppTextStyles.normalTextBold,
                )
                Text(
                    text = state.filteredRecipesText,
                    style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(state.filteredRecipes) {
                    SearchRecipeCard(
                        recipe = it
                    )
                }

            }
        }

        if (showBottomSheet) {
            FilterSearchBottomSheet(
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                onDismiss = {
                    tapFilterButton(false)
                },
                currentFilterState = state.filterState,
                onApplyFilter = {
                    onApplyFilter(it)
                    tapFilterButton(false)
                }
            )
        }
    }
}

@Preview
@Composable
private fun SearchRecipeScreenPreview() {
    SearchRecipeScreen(
        state = SearchRecipeState(
            searchQuery = "query",
            searchText = "Search Result",
            filteredRecipesText = "10 results",
            filteredRecipes = listOf(
                Recipe(
                    title = "Traditional spare ribs baked",
                    chef = "Chef John",
                    time = "20 min",
                    category = "Chinese",
                    rating = 4.0,
                    imageUrls = "https://cdn.pixabay.",
                    createdAt = System.currentTimeMillis(),
                    id = 0
                )
            ),
        ),
    )
}