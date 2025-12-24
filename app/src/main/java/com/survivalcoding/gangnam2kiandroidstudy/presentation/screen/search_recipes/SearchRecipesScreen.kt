package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items.SearchResultCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.searchbar.SearchBarContainer
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRecipesScreen(
    modifier: Modifier = Modifier,
    onAction: (SearchRecipesAction) -> Unit = {},
    state: SearchRecipesState,
) {
    val focusRequester = remember { FocusRequester() }

    // 최초 진입 시에만 포커스 요청
    LaunchedEffect(state.hasRequestedFocus) {
        if (!state.hasRequestedFocus) {
            focusRequester.requestFocus()
            onAction(SearchRecipesAction.FocusRequested)
        }
    }

    LazyColumn(
        modifier = modifier
    ) {

        // Search bar + filter button
        item {
            SearchBarContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                value = state.searchKeyword,
                onValueChange = {
                    onAction(SearchRecipesAction.KeywordChanged(it))
                },
                onFilterClick = {
                    onAction(SearchRecipesAction.FilterClicked)
                },
                focusRequester = focusRequester
            )
        }

        // Recent text
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // 왼쪽 텍스트
                Text(
                    text = stringResource(state.headerTitleRes),
                    style = AppTextStyles.normalTextBold
                )

                // “XX results”
                state.resultCountResId?.let { resId ->
                    Text(
                        text = stringResource(
                            id = resId, state.filteredRecipes.size
                        ),
                        style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                    )
                }
            }
        }

        // Grid 만들기
        items(state.filteredRecipes.chunked(2)) { rowItems ->
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
                        chef = recipe.chefName,
                        rating = recipe.rating,
                        onClick = {
                            onAction(SearchRecipesAction.RecipeClicked(recipe.id))
                        }

                    )
                }

                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun SearchRecipesScreenPreview() {
    // SearchRecipesScreen()
}
