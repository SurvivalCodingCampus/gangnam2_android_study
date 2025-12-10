package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items.SearchResultCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.appbar.CustomAppTopBar
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.FilterSearchBottomSheet
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.searchbar.SearchBarContainer
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRecipesScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchRecipesViewModel = viewModel(
        factory = SearchRecipesViewModel.Factory
    )
) {
    val searchState by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = AppColors.white,
        topBar = {
            CustomAppTopBar(
                text = stringResource(R.string.search_recipes_title),
                showBackButton = true
            )
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            // Search bar + filter button
            item {
                SearchBarContainer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    onFilterClick = { viewModel.showBottomSheet(true) }
                )
            }

            // Recent text
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    text = stringResource(R.string.recent_search_subtitle),
                    style = AppTextStyles.normalTextBold,
                    textAlign = TextAlign.Left
                )
            }

            // Grid 만들기
            items(searchState.filteredRecipes.chunked(2)) { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {

                    rowItems.forEach { recipe ->
                        SearchResultCard(
                            modifier = Modifier
                                .weight(1f)
                                .height(150.dp),
                            name = recipe.title,
                            imageUrl = recipe.imageUrls,
                            chef = recipe.chef,
                            rating = recipe.rating
                        )
                    }

                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

        }
        if (searchState.showBottomSheet) {
            FilterSearchBottomSheet(
                onDismiss = {
                    viewModel.showBottomSheet(false)
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SearchRecipesScreenPreview() {
    SearchRecipesScreen()
}
