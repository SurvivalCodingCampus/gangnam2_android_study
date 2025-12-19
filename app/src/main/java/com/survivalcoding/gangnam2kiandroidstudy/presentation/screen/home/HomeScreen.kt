package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.*
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.card.HomeRecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.card.NewRecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun HomeScreen(
    state: HomeState = HomeState(),
    onAction: (HomeAction) -> Unit,
    profilePainter: Painter,
) {

    // 화면 전용 상태
    // 카드 선택(회색/컬러)
    var selectedRecipeId by remember { mutableStateOf<Int?>(null) }
    // 북마크 토글 (임시 UI 상태)
    var bookmarkedRecipeIds by remember { mutableStateOf(setOf<Int>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.white),
    ) {

        Spacer(modifier = Modifier.height(64.dp))

        // 상단 인사 + 프로필 영역
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Hello Jega",
                    style = AppTextStyles.largeTextBold
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "What are you cooking today?",
                    style = AppTextStyles.smallerTextRegular.copy(
                        color = AppColors.gray3
                    )
                )
            }

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = AppColors.secondary40,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Image(
                    painter = profilePainter,
                    contentDescription = "Profile Image",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // 검색 + 필터 영역
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchInputField(
                modifier = Modifier.weight(1f),
                value = "",
                placeholder = "Search Recipe",
                readOnly = true,
                onValueChange = {},
                onClick = { onAction(HomeAction.ClickSearch) }
            )

            Spacer(modifier = Modifier.width(20.dp))

            FilterBox {
                onAction(HomeAction.ClickFilter)
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        // 카테고리 선택 영역
        RecipeCategorySelector(
            categories = listOf(
                "All", "Indian", "Italian", "Asian", "Chinese",
                "Fruit", "Vegetables", "Protein", "Cereal", "Local Dishes"
            ),
            selectedCategory = state.selectedCategory,
            onSelectCategory = { category ->
                onAction(HomeAction.ClickCategory(category))
            },
        )

        Spacer(modifier = Modifier.height(15.dp))

        // 홈 레시피 카드
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(state.filteredRecipes) { recipe ->

                val isSelected = selectedRecipeId == recipe.id
                val isBookmarked = bookmarkedRecipeIds.contains(recipe.id)

                HomeRecipeCard(
                    recipe = recipe,
                    isSaved = isBookmarked,
                    modifier = Modifier.clickable {
                        selectedRecipeId =
                            if (isSelected) null else recipe.id
                    },
                    onBookmarkClick = {
                        bookmarkedRecipeIds =
                            if (isBookmarked) {
                                bookmarkedRecipeIds - recipe.id
                            } else {
                                bookmarkedRecipeIds + recipe.id
                            }
                    }
                )
            }
        }

        // New Recipes
        Spacer(modifier = Modifier.height(30.dp))

        // 타이틀
        Text(
            text = "New Recipes",
            style = AppTextStyles.mediumTextBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        // 리스트
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(state.newRecipes) { recipe ->
                NewRecipeCard(
                    recipe = recipe,
                    onClick = {
                    }
                )
            }
        }
    }
}


// Preview
@Preview(showBackground = true)
@Composable
private fun HomeScreenPrev() {
    HomeScreen(
        state = HomeState(),
        profilePainter = ColorPainter(Color.Gray),
        onAction = {}
    )
}
