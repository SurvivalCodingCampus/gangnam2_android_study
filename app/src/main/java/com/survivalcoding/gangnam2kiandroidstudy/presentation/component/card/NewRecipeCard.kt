package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.card

import android.graphics.Color.BLUE
import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.recipe.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun NewRecipeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit = {},
) {
    Box(
        modifier = modifier
            .height(127.dp)
            .aspectRatio(251 / 127f)
            .clickable { onClick(recipe.id) }
    ) {


        Column(modifier = Modifier.fillMaxSize()) {

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)
                    .shadow(
                        elevation = 20.dp,
                        shape = RoundedCornerShape(10.dp),
                        ambientColor = Color.Black.copy(alpha = 0.3f),
                        spotColor = Color.Black.copy(alpha = 0.3f)
                    )
                    .background(
                        color = AppColors.white,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(vertical = 10.dp)
                    .padding(start = 10.dp)
            ) {
                // 제목
                Text(
                    text = recipe.name,
                    style = AppTextStyles.smallTextBold,
                    modifier = Modifier.width(150.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                // 별점
                Row {
                    repeat(recipe.rating.toInt()) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Filled Star",
                            tint = AppColors.rating,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                    repeat(5 - recipe.rating.toInt()) {
                        Icon(
                            imageVector = Icons.Outlined.StarOutline,
                            contentDescription = "Outlined Star",
                            tint = AppColors.rating,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // 하단 정보
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Chef
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.chef_profile),
                            contentDescription = "Chef",
                            modifier = Modifier
                                .size(25.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "By ${recipe.chef}",
                            style = AppTextStyles.smallerTextRegular.copy(
                                color = AppColors.gray3
                            )
                        )
                    }

                    // Time
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_outline_timer),
                            contentDescription = "Time",
                            tint = AppColors.gray4,
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = recipe.time,
                            style = AppTextStyles.smallerTextRegular.copy(
                                color = AppColors.gray3
                            )
                        )

                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            }
        }

        // 우측 이미지
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(width = 80.dp, height = 86.dp)
                    .clip(CircleShape),
                model = if (LocalInspectionMode.current) {
                    ColorDrawable(BLUE)
                } else {
                    recipe.imageUrl
                },
                contentDescription = recipe.name,
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewRecipeCardPreview() {
    NewRecipeCard(
        recipe = Recipe(
            id = 1,
            name = "Steak with tomato sauce",
            chef = "James Milner",
            time = "20 min",
            category = "Dinner",
            rating = 4.0,
            imageUrl = "",
            createdAt = System.currentTimeMillis()
        )
    )
}
