package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.HomeImage
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.AverageRatingButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.BookMarkButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun HomeRecipeCard(
    recipe: Recipe,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(150.dp, 231.dp)
    ) {
        // 아래쪽 회색 카드
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)   // 부모 Box의 아래 중앙
                .size(150.dp, 176.dp)
                .background(
                    color = AppColors.gray4,
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable{ onClick() }
            ,
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                modifier = Modifier
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 제목
                Box(
                    modifier = Modifier.height(42.dp)) {
                    Text(
                        text = recipe.title,
                        style = AppTextStyles.smallTextBold.copy(
                            color = AppColors.gray1
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.home_recipe_card_time),
                            style = AppTextStyles.smallerTextRegular.copy(
                                color = AppColors.gray3
                            )
                        )
                        Text(
                            text = recipe.time,
                            style = AppTextStyles.smallerTextBold.copy(
                                color = AppColors.gray1
                            )
                        )
                    }

                    BookMarkButton(
                        isBookmarked = isBookmarked,
                        onClick = onBookmarkClick
                    )
                }
            }
        }

        Image(
            painter = painterResource(recipe.homeImage.resId),
            contentDescription = null,
            modifier = Modifier
                .size(110.dp)
                .align(Alignment.TopCenter)  // 부모 Box의 위쪽 중앙
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        AverageRatingButton(
            rating = recipe.rating,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-10).dp, y = 35.dp)
        )

    }
}


@Preview
@Composable
private fun HomeRecipeCardPreview() {
    val sample = Recipe(
        id = 1,
        title = "Sample Salad",
        chefId = 1,
        chefName = "Chef",
        time = "15 min",
        rating = 4.5,
        imageUrls = "",
        createdAt = 0L,
        category = "Cereal",
        homeImage = HomeImage.FOOD5
    )
    HomeRecipeCard(
        recipe = sample,
        isBookmarked = true,
        onBookmarkClick = {},
        onClick = {}
    )
}