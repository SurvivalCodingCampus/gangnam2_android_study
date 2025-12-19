package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import android.graphics.Color.BLUE
import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun NewRecipeCard(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    onClick: (Int) -> Unit = {}
) {
    Box(
        modifier
            .height(127.dp)
            .aspectRatio(251 / 127f)
            .clickable {
                onClick(recipe.id)
            }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)
                    .background(color = AppColors.white, shape = RoundedCornerShape(10.dp))
                    .padding(vertical = 10.dp)
                    .padding(start = 9.3.dp)
            ) {
                Text(
                    text = recipe.title,
                    style = AppTextStyles.smallTextBold,
                    modifier = Modifier.width(140.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(5.dp))

                Row {
                    repeat(recipe.rating.toInt()) {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = "Filled Star",
                            tint = AppColors.rating,
                            modifier = Modifier.size(11.16.dp, 12.dp)
                        )
                        Spacer(modifier = Modifier.width(1.86.dp))
                    }
                    repeat(5 - recipe.rating.toInt()) {
                        Icon(
                            Icons.Outlined.StarOutline,
                            contentDescription = "Outlined Star",
                            tint = AppColors.rating,
                            modifier = Modifier.size(11.16.dp, 12.dp)
                        )
                        Spacer(modifier = Modifier.width(1.86.dp))
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(25.dp)
                                .clip(CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ingrdeintimage),
                                contentDescription = "profile image",
                                modifier = Modifier.size(25.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "By ${recipe.chef}",
                            style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_timer),
                            contentDescription = "Cooking Time",
                            tint = AppColors.gray4,
                            modifier = Modifier
                                .size(17.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = recipe.time,
                            style = AppTextStyles.smallerTextRegular.copy(
                                color = AppColors.gray3
                            ),
                            maxLines = 1,
                            modifier = Modifier.padding(start = 5.dp, end = 10.dp)
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                    }
                }

            }
        }
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
                    recipe.imageUrls
                },
                contentDescription = recipe.title,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(9.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewRecipeCardPreview() {
    NewRecipeCard(
        recipe = Recipe(
            title = "Spicy fried rice mix chicken bali",
            chef = "Spicy Nelly",
            id = 1,
            time = "20 min",
            category = "Chinese",
            rating = 4.0,
            imageUrls = "https://cdn.pixabay.",
            createdAt = 121882118,
            address = "Seoul"
        )
    )

}