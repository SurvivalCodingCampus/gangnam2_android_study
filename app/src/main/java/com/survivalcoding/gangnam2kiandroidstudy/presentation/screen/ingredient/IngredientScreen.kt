package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Chef
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items.IngredientListItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.ingredient.IngredientSummaryRow
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.tabs.DualTabBar
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.user.RecipeAuthorSection
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun IngredientScreen(
    state: IngredientState,
    onTabSelected: (Int) -> Unit,
    onFollowClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        // Recipe Card
        item {
            state.recipe?.let { recipe ->
                RecipeCard(
                    name = recipe.title,
                    imageUrl = recipe.imageUrls,
                    chef = recipe.chefName,
                    time = recipe.time,
                    rating = recipe.rating,
                    isBookmarked = false,
                    onBookmarkClick = {},
                    onClick = {},
                    showTitleAndChef = false
                )
            }
        }

        // Recipe Title + Reviews
        item {
            state.recipe?.let { recipe ->
                Row(
                    modifier = Modifier
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = recipe.title,
                        style = AppTextStyles.smallTextBold,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(18.dp))

                    Text(
                        text = "(13K Reviews)", // TODO: 실제 리뷰 수 연동
                        style = AppTextStyles.smallTextRegular.copy(
                            color = AppColors.gray3
                        )
                    )
                }
            }
        }


        // Author Section
        state.author?.let { author ->
            item {
                RecipeAuthorSection(
                    modifier = Modifier.padding(bottom = 8.dp),
                    author = author,
                    isFollowing = state.isFollowing,
                    onFollowClick = onFollowClick
                )
            }
        }

        // Tabs (Ingredients / Procedure)
        item {
            DualTabBar(
                leftTab = "Ingredients",
                rightTab = "Procedure",
                selectedIndex = state.selectedTab,
                onTabSelected = onTabSelected
            )
        }

        item {
            IngredientSummaryRow(
                serveCount = 1, // 고정
                ingredientCount = state.ingredients.size
            )
        }

        // Ingredients List
        if (state.selectedTab == 0) {
            items(state.ingredients) { ingredient ->
                IngredientListItem(
                    modifier = Modifier.padding(bottom = 10.dp),
                    name = ingredient.name,
                    imageUrl = ingredient.image,
                    amount = ingredient.amount
                )
            }
        }

        // Procedure List
        if (state.selectedTab == 1) {
            items(state.procedures) { step ->
//                    IngredientProcedureItem(
//                        text = step
//                    )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun IngredientScreenPreview() {
    val previewState = IngredientState(
        author = Chef(
            id = 1,
            name = "Chef John",
            imageUrls = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            address = "Lagos, Nigeria"
        ),
        ingredients = listOf(
            IngredientItem(
                id = 1,
                name = "Tomatoes",
                image = "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
                amount = 500
            ),
            IngredientItem(
                id = 2,
                name = "Onion",
                image = "https://cdn.pixabay.com/photo/2016/03/05/19/02/onion-1239117_1280.jpg",
                amount = 200
            )
        ),
        procedures = listOf(
            "Wash all vegetables thoroughly.",
            "Chop the tomatoes and onions.",
            "Cook over medium heat for 10 minutes."
        ),
        selectedTab = 0,
        isFollowing = false
    )

    IngredientScreen(
        state = previewState,
        onTabSelected = {},
        onFollowClick = {}
    )
}
