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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

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
                    text = "(${uiState.reviewCount} Reviews)",
                    style = AppTextStyles.smallTextBold.copy(AppColors.gray3),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }




    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailsScreenPreview() {
    RecipeDetailsScreen(
        uiState = RecipeDetailsState(
            recipe = Recipe(
                id = 1,
                name = "Kimchi Fried Rice",
                image = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
                chef = "Chef John",
                time = "20 min",
                rating = 4.0,
                category = "Korean",
            ),
            reviewCount = 1,
        )
    )
}
