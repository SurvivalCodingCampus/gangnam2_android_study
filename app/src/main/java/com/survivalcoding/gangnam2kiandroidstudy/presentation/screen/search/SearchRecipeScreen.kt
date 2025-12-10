package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSettingButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeSearchCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Search
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SearchRecipeScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchRecipeViewModel = viewModel(factory = SearchRecipeViewModel.Factory),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(27.dp),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow Back Icon",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(69.dp))
                Text(
                    text = "Search recipe",
                    style = AppTextStyles.mediumTextBold.copy(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                )
            }
            Spacer(Modifier.height(17.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Search(
                    modifier = Modifier.weight(1f),
                    placeholder = "Search recipe",
                    value = searchQuery,
                    onClick = { viewModel.performSearch() },
                    onValueChange = { viewModel.updateSearch(it) })
                Spacer(Modifier.width(20.dp))

                FilterSettingButton {
                    viewModel.showBottomSheet()
                }
            }
            Spacer(Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (searchQuery.isNotEmpty()) "Search Result" else "Recent Search",
                    modifier = Modifier
                        .weight(1f),
                    style = AppTextStyles.normalTextBold
                )

                Text(
                    text = if (uiState.data.isNotEmpty()) "${uiState.data.size} results" else "",
                    style = AppTextStyles.smallerTextRegular,
                    color = AppColors.gray3
                )
            }
            Spacer(Modifier.height(20.dp))

            if (uiState.loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(uiState.data) { recipe ->
                        RecipeSearchCard(recipe = recipe)
                    }
                }
            }

            FilterSearchBottomSheet(
                uiState = uiState,
                showBottomSheet = uiState.showBottomSheet,
                onDismiss = { viewModel.hideBottomSheet() },
                onTimeSelected = { viewModel.updateFilterTime(it) },
                onRateSelected = { viewModel.updateFilterRate(it) },
                onCategorySelected = { viewModel.updateFilterCategory(it) },
                onFilterClick = { time, rate, category ->
                    viewModel.updateFilter(time, rate, category)
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchRecipeScreenPreview() {
    Scaffold { innerPadding ->
        SearchRecipeScreen(modifier = Modifier.padding(innerPadding))
    }
}