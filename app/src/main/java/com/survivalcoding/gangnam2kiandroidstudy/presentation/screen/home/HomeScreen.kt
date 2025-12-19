package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items.HomeRecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items.NewRecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.searchbar.SearchBarContainer
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.selector.HomeCategory
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.selector.RecipeCategorySelector
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun HomeScreen(
    state: HomeState,
    onAction: (HomeAction) -> Unit = {},
    onNavigateToSearch: () -> Unit,
    onRecipeClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()

    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.padding(top = 10.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = stringResource(R.string.home_title),
                        style = AppTextStyles.largeTextBold,
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = stringResource(R.string.home_subtitle),
                        style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3),
                        lineHeight = 17.sp,
                        modifier = Modifier.size(195.dp, 17.dp)
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.src_profile),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }

        }

        // Search bar + filter button - TODO: 검색창이나 필터 선택시 검색 화면으로 이동 후 기능 동작
        item {
            SearchBarContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                value = "",
                onValueChange = {},
                onFilterClick = {},
                onClick = onNavigateToSearch
            )
        }

        // Recipe category selector
        item {
            RecipeCategorySelector(
                modifier = Modifier.padding(vertical = 25.dp),
                selected = state.selectedCategory,
                onSelectCategory = {
                    onAction(HomeAction.SelectCategory(it))
                }
            )

        }

        item {
            LazyRow(
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .fillMaxWidth()
            ) {
                items(state.filteredRecipes) { recipe ->
                    val isBookmarked = recipe.id in state.bookmarkedIds

                    HomeRecipeCard(
                        recipe = recipe,
                        isBookmarked = isBookmarked,
                        onBookmarkClick = {
                            onAction(HomeAction.ToggleBookmark(recipe.id))
                        },
                        onClick = {
                            onRecipeClick(recipe.id)
                        },
                        modifier = Modifier.padding(end = 15.dp)
                    )
                }
            }
        }

        item {
            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = stringResource(R.string.home_new_recipes_title),
                style = AppTextStyles.normalTextBold
            )
        }

        item { Spacer(Modifier.height(5.dp)) }


        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                items(state.newRecipes) { recipe ->
                    NewRecipeCard(
                        newRecipe = recipe,
                        modifier = Modifier,
                        onClick = { onRecipeClick(recipe.recipeId) }
                    )
                }
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
fun RecipeCategorySelectorPreview() {
    val fakeState = HomeState(
        selectedCategory = HomeCategory.ALL
    )

    HomeScreen(
        state = fakeState,
        onAction = {},
        onNavigateToSearch = {},
        onRecipeClick = {}
    )
}
