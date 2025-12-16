package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import android.graphics.Color.BLUE
import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun HomeRecipeCard(
    modifier: Modifier = Modifier,
    recipe: Recipe,
) {
    Box(
        modifier = modifier
            .height(231.dp)
            .aspectRatio(150 / 231f)
            .background(AppColors.white)
    ) {
        Box(
            modifier = modifier
                .height(176.dp)
                .aspectRatio(150 / 176f)
                .background(color = AppColors.gray4, shape = RoundedCornerShape(12.dp))
                .align(Alignment.BottomCenter)
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(22.dp))
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape),
                        model = if (LocalInspectionMode.current) {
                            ColorDrawable(BLUE)
                        } else {
                            recipe.imageUrls
                        },
                        contentDescription = recipe.title,
                        contentScale = ContentScale.Crop
                    )

                    Row(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(45.dp)
                            .height(23.dp)
                            .offset(x = 0.dp, y = 30.dp)
                            .background(
                                color = AppColors.secondary20,
                                shape = RoundedCornerShape(size = 20.dp)
                            )
                            .padding(horizontal = 7.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = AppColors.rating,
                            modifier = Modifier.size(10.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = recipe.rating.toString(),
                            style = AppTextStyles.smallerTextRegular,
                            modifier = Modifier.size(17.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(11.dp))
            Text(
                text = recipe.title,
                style = AppTextStyles.smallTextBold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                maxLines = 2,
                minLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(19.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Time",
                        style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = recipe.time,
                        style = AppTextStyles.smallerTextBold
                    )
                }
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(AppColors.primary20)
                        .align(Alignment.Bottom),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_bookmark_inactive),
                        contentDescription = "bookmark Recipe",
                        tint = AppColors.primary80,
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeRecipeCardPreview() {
    HomeRecipeCard(
        recipe = Recipe(
            title = "Spicy fried rice mix chicken bali",
            chef = "Spicy Nelly",
            time = "20 min",
            category = "Chinese",
            rating = 4.0,
            imageUrls = "https://cdn.pixabay.com/photo/2019/09/07/19/02/spanish-paella-4459519_1280.jpg",
            createdAt = 121882118,
            id = 0
        ),
    )
}