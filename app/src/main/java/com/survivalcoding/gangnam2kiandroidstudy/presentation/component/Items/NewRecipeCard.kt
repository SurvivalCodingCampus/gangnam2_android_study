package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.HomeImage
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NewRecipe
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles


@Composable
fun NewRecipeCard(
    newRecipe: NewRecipe,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.size(
            251.dp, 127.dp
        )
    ) {
        Box(
            modifier = Modifier
                .size(251.dp, 95.dp)
                .align(Alignment.BottomCenter)
                .shadow(
                    elevation = 10.dp,
                    ambientColor = AppColors.black.copy(alpha = 0.20f),
                    spotColor = AppColors.black.copy(alpha = 0.20f),
                    shape = RoundedCornerShape(10.dp),
                    clip = false
                )
                .background(
                    color = AppColors.white,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(vertical = 10.dp, horizontal = 9.3.dp)
                .clickable { onClick() }
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // 레시피 타이틀
                Text(
                    text = newRecipe.title,
                    style = AppTextStyles.smallTextBold.copy(color = AppColors.gray1),
                    maxLines = 1
                )
                Spacer(Modifier.height(5.dp))

                // 별점 아이콘
                Row {
                    NewRecipeCardRating(newRecipe.rating)
                }

                Spacer(Modifier.weight(1f))

                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = newRecipe.chefProfileImageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .size(25.dp)
                                .clip(CircleShape)
                        )

                        Spacer(Modifier.width(8.dp))

                        Text(
                            text = newRecipe.chefName,
                            style = AppTextStyles.smallerTextRegular.copy(
                                AppColors.gray3
                            ),
                        )

                    }
                    Spacer(Modifier.weight(1f))
                    // 시계 아이콘
                    Row(
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_timer_outline),
                            contentDescription = null,
                            tint = AppColors.gray3,
                            modifier = Modifier.size(17.dp)
                        )
                        Spacer(Modifier.width(5.dp))
                        Text(
                            text = newRecipe.time,
                            style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                        )
                    }
                }

            }

        }
        AsyncImage(
            model = newRecipe.image,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.TopEnd)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }

}

@Composable
private fun NewRecipeCardRating(
    rating: Double,
    modifier: Modifier = Modifier
) {
    val filledCount = rating.toInt().coerceIn(0, 5)
    val emptyCount = 5 - filledCount

    Row(modifier = modifier) {
        repeat(filledCount) {
            Icon(
                painter = painterResource(R.drawable.ic_star_6),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(10.5.dp)
                    .padding(0.75.dp)
            )
        }

        repeat(emptyCount) {
            Icon(
                painter = painterResource(R.drawable.ic_star_2),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(10.5.dp)
                    .padding(0.75.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun NewRecipeCardPreview() {

    val sample = NewRecipe(
        recipeId = 1,
        title = "Sample Salad",
        rating = 4.5,
        time = "15 min",
        image = "",
        chefName = "Chef",
        createdAt = 0L,
        chefProfileImageUrl = "",
    )

    NewRecipeCard(
        newRecipe = sample
    )
}