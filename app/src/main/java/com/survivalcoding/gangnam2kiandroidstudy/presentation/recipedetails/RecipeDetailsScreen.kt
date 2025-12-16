package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Tabs
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import kotlinx.collections.immutable.ImmutableList

@Composable
fun RecipeDetailsScreen(
    modifier: Modifier = Modifier,
    uiState: RecipeDetailsState = RecipeDetailsState(),
    onTabClick: (Int) -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                painter = painterResource(R.drawable.outline_arrow_right),
                contentDescription = "back icon",
                modifier = Modifier
                    .size(20.dp)
                    .rotate(180f)
                    .clickable { onBackClick() },
            )
            Icon(
                painter = painterResource(R.drawable.outline_more),
                contentDescription = "more icon",
                modifier = Modifier
                    .size(24.dp),
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            uiState.recipe?.let {
                RecipeCard(
                    recipe = it,
                )
            }
        }

        Row(
            modifier = Modifier.padding(horizontal = 5.dp),
        ) {
            Text(
                text = uiState.recipe?.name ?: "",
                style = AppTextStyles.smallTextBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 18.dp),
            )

            Text(
                text = "(13K Reviews)",
                style = AppTextStyles.smallTextBold.copy(AppColors.gray3),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = uiState.profile?.image,
                    contentDescription = "profile image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(
                        text = uiState.profile?.name ?: "",
                        style = AppTextStyles.smallTextBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(1.dp),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.bold_location),
                            contentDescription = "location icon",
                            tint = AppColors.primary80,
                            modifier = Modifier.size(17.dp),
                        )
                        Text(
                            text = uiState.profile?.address ?: "",
                            style = AppTextStyles.smallTextBold.copy(AppColors.gray3),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }

            SmallButton(
                text = "Follow",
                modifier = Modifier.width(100.dp),
            ) {}
        }


        Tabs(
            labels = listOf("Ingredient", "Procedure") as ImmutableList<String>,
            selectedIndex = uiState.selectedTabIndex,
            onTabSelected = onTabClick,
        )




        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 22.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_timer_outline),
                    contentDescription = "timer icon",
                    tint = AppColors.gray3,
                    modifier = Modifier.size(17.dp),
                )
                Text(
                    text = "1 serve",
                    style = AppTextStyles.smallTextRegular.copy(AppColors.gray3),
                )
            }

            if (uiState.selectedTabIndex == 0) {
                Text(
                    text = "${uiState.ingredients.size} items",
                    style = AppTextStyles.smallerTextRegular.copy(AppColors.gray3),
                )
            }

            if (uiState.selectedTabIndex == 1) {
                Text(
                    text = "${uiState.procedures.size} steps",
                    style = AppTextStyles.smallTextRegular.copy(AppColors.gray3),
                )
            }
        }






    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailsScreenPreview() {
    RecipeDetailsScreen(

    )
}
