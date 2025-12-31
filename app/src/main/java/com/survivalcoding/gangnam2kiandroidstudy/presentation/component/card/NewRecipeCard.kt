package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.card

import android.graphics.Color
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
    enableAnimation: Boolean,
    onClick: (Int) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .height(127.dp)
            .aspectRatio(251 / 127f)
            .clickable { onClick(recipe.id) }
    ) {

        // 카드 본문
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(32.dp)) // 이미지 겹침 여백

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)
                    .background(
                        color = AppColors.white,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(vertical = 10.dp)
                    .padding(horizontal = 10.dp)
            ) {

                // 제목
                Text(
                    text = recipe.name,
                    style = AppTextStyles.smallTextBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                // 평점
                Row {
                    repeat(recipe.rating.toInt()) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = AppColors.rating,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                    }
                    repeat(5 - recipe.rating.toInt()) {
                        Icon(
                            imageVector = Icons.Outlined.StarOutline,
                            contentDescription = null,
                            tint = AppColors.rating,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // 하단
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    // 셰프 정보
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.chef_profile),
                            contentDescription = null,
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

                    // 조리 시간
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_outline_timer),
                            contentDescription = null,
                            tint = AppColors.gray4,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = recipe.time,
                            style = AppTextStyles.smallerTextRegular.copy(
                                color = AppColors.gray3
                            )
                        )
                    }
                }
            }
        }

        // 음식 이미지
        if (enableAnimation) {
            AsyncImage(
                modifier = Modifier
                    .size(width = 80.dp, height = 86.dp)
                    .align(Alignment.TopEnd)
                    .clip(CircleShape),
                model = if (LocalInspectionMode.current) {
                    // Preview / 테스트 안정용 더미 이미지
                    ColorDrawable(Color.LTGRAY)
                } else {
                    recipe.imageUrl
                },
                contentDescription = recipe.name,
                contentScale = ContentScale.Crop
            )
        } else {
            // UI 테스트용 이미지 대체 박스
            Box(
                modifier = Modifier
                    .size(width = 80.dp, height = 86.dp)
                    .align(Alignment.TopEnd)
                    .clip(CircleShape)
                    .background(AppColors.gray3)
            )
        }
    }
}

//Preview
@Preview(showBackground = true)
@Composable
private fun NewRecipeCardPreview() {
    NewRecipeCard(
        recipe = Recipe(
            id = 1,
            name = "Spicy fried rice mix chicken bali",
            chef = "Spicy Nelly",
            time = "20 min",
            category = "Chinese",
            rating = 4.0,
            imageUrl = "https://cdn.pixabay.com/photo/2019/09/07/19/02/spanish-paella-4459519_1280.jpg",
            createdAt = System.currentTimeMillis(),
            address = "Seoul",
        ),
        enableAnimation = true
    )
}
