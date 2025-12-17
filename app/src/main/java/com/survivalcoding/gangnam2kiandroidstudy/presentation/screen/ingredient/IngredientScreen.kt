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
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items.IngredientListItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.ingredient.IngredientSummaryRow
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.ingredient.ProcedureCard
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
    val detail = state.recipeDetail ?: return
    LazyColumn(
        modifier = modifier
    ) {


        // Recipe Card
        item {
            RecipeCard(
                name = detail.recipe.title,
                imageUrl = detail.recipe.imageUrls,
                chef = detail.recipe.chefName,
                time = detail.recipe.time,
                rating = detail.recipe.rating,
                isBookmarked = false,
                onBookmarkClick = {},
                onClick = {},
                showTitleAndChef = false
            )
        }

        // Recipe Title + Reviews
        item {
            Row(
                modifier = Modifier.padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = detail.recipe.title,
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


        // Author Section
        detail.chef?.let { author ->
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
                serveCount = 1,
                rightLabel =
                    if (state.selectedTab == 0)
                        "${detail.ingredients.size} Items"
                    else
                        "${detail.procedures.size} Steps"
            )
        }

        // Ingredients List
        if (state.selectedTab == 0) {
            items(detail.ingredients) { ingredient ->
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
            items(detail.procedures) { procedure ->
                ProcedureCard(
                    step = procedure.step,
                    content = procedure.content,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun IngredientScreenPreview() {

}
