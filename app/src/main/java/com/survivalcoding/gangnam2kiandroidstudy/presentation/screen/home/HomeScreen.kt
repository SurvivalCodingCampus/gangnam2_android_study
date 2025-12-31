package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.*
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.card.HomeRecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.card.NewRecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun HomeScreen(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
    profilePainter: Painter,
    isTest: Boolean = false,
) {
    if (state.errorMessage != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.white)
                .testTag("home_error"),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = state.errorMessage,
                style = AppTextStyles.mediumTextBold,
                color = AppColors.gray3
            )
        }
        return
    }

    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.white),
    ) {

        Spacer(modifier = Modifier.height(64.dp))

        // 상단바
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Hello Jega", style = AppTextStyles.largeTextBold)
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    "What are you cooking today?",
                    style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                )
            }

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(AppColors.secondary40, RoundedCornerShape(10.dp))
            ) {
                Image(
                    painter = profilePainter,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // 검색
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
            FilterBox { onAction(HomeAction.ClickFilter) }
        }

        Spacer(modifier = Modifier.height(15.dp))

        RecipeCategorySelector(
            categories = listOf(
                "All", "Indian", "Italian", "Asian", "Chinese",
                "Fruit", "Vegetables", "Protein", "Cereal", "Local Dishes"
            ),
            selectedCategory = state.selectedCategory,
            onSelectCategory = { onAction(HomeAction.ClickCategory(it)) },
        )

        Spacer(modifier = Modifier.height(15.dp))

        // HomeRecipeCard
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(state.filteredRecipes) { recipe ->
                HomeRecipeCard(
                    recipe = recipe,
                    isSaved = state.bookmarkedRecipeIds.contains(recipe.id),
                    enableAnimation = !isTest,
                    onClick = {
                        onAction(HomeAction.OpenRecipeDetail(recipe.id))
                    },
                    onBookmarkClick = {
                        onAction(HomeAction.ToggleRecipeBookmark(recipe.id))
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            "New Recipes",
            style = AppTextStyles.mediumTextBold,
            modifier = Modifier.padding(horizontal = 30.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        // NewRecipeCard
        LazyRow(
            modifier = Modifier.padding(start = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(state.newRecipes) { recipe ->
                NewRecipeCard(
                    recipe = recipe,
                    enableAnimation = !isTest
                )
            }
        }
    }
}


