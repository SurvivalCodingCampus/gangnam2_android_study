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
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.searchbar.SearchBarContainer
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.selector.HomeCategory
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.selector.RecipeCategorySelector
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun HomeScreen(
    state: HomeState,
    onSelectCategory: (HomeCategory) -> Unit,
    onSearchClick: () -> Unit,
    onBookmarkClick: (Int) -> Unit,
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
                onClick = onSearchClick
            )
        }

        // Recipe category selector
        item {
            RecipeCategorySelector(
                modifier = Modifier.padding(vertical = 25.dp),
                selected = state.selectedCategory,
                onSelectCategory = onSelectCategory
            )

        }

        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(state.filteredRecipes) { recipe ->
                    val isBookmarked = recipe.id in state.bookmarkedIds

                    HomeRecipeCard(
                        recipe = recipe,
                        isBookmarked = isBookmarked,
                        onBookmarkClick = {
                            onBookmarkClick(recipe.id)
                        },
                        modifier = Modifier.padding(end = 15.dp)
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
        onSelectCategory = {},
        onSearchClick = {},
        onBookmarkClick = {},
    )
}
