package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.DishCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCategorySelector
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SearchBar
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun HomeScreen(
    state: HomeState = HomeState(),
    onCategoryClick: (String) -> Unit = {},
    onSearchQueryChange: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 64.dp,
                    start = 30.dp,
                    bottom = 30.dp,
                    end = 30.dp,
                ),
        ) {
            Column(
            ) {
                Text(
                    text = "Hello Lee",
                    style = AppTextStyles.largeTextBold,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "What are you cooking today?",
                    style = AppTextStyles.smallerTextRegular,
                    color = AppColors.gray3,
                )
            }

            Image(
                painter = painterResource(R.drawable.ic_profile_outline),
                contentDescription = "프로필 이미지",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterEnd)
                    .background(
                        color = AppColors.secondary40,
                        shape = RoundedCornerShape(10.dp)
                    )
            )
        }

        // 검색창
        SearchBar(
            modifier = Modifier.padding(horizontal = 30.dp),
            query = state.searchText,
            onQueryChange = onSearchQueryChange,
            onFilterClick = {

            }
        )

        // 카테고리 선택바
        RecipeCategorySelector(
            modifier = Modifier.padding(vertical = 15.dp),
            selectedCategory = state.selectedCategory,
            onCategoryClick = onCategoryClick,
        )

        // dish cards
        LazyRow() {
            item {
                Spacer(modifier = Modifier.width(30.dp))
            }

            items(state.filteredRecipes) { item ->
                DishCard(
                    recipe = item,
                    onDishClick = {},
                    onBookmarkClick = {},
                    isSaved = false,
                    )
            }
        }

        // New Recipes
        Text(
            text = "New Recipes",
            style = AppTextStyles.normalTextBold,
            modifier = Modifier.padding(
                top = 20.dp,
                start = 30.dp,
                end = 5.dp,
            )
        )



    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
