package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.card

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.recipe.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun HomeRecipeCard(
    recipe: Recipe,
    isSaved: Boolean,
    onBookmarkClick: () -> Unit,
    onClick: () -> Unit,
    enableAnimation: Boolean,
) {
    Box(
        modifier = Modifier
            .size(width = 150.dp, height = 231.dp)
            .clickable { onClick() }
    ) {

        // 카드배경
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(176.dp)
                .background(
                    color = AppColors.gray4.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(12.dp)
                )
                .align(Alignment.BottomCenter)
                .padding(10.dp)
        ) {
            Text(
                text = recipe.name,
                style = AppTextStyles.smallTextBold.copy(
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                ),
                color = AppColors.gray1,
                modifier = Modifier
                    .padding(top = 56.dp)
                    .fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                Text("Time", style = AppTextStyles.smallerTextRegular, color = AppColors.gray3)
                Spacer(modifier = Modifier.height(5.dp))
                Text(recipe.time, style = AppTextStyles.smallerTextBold)
            }

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.BottomEnd)
                    .background(AppColors.white, CircleShape)
                    .clickable { onBookmarkClick() }
                    .testTag("bookmark_icon_${recipe.id}")
                    .semantics { selected = isSaved },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_outline_bookmark_inactive),
                    contentDescription = "bookmark Recipe",
                    tint = if (isSaved) AppColors.primary80 else AppColors.gray3,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        // 이미지
        if (enableAnimation) {
            AsyncImage(
                model = if (LocalInspectionMode.current) {
                    // Preview / 테스트 안정용 더미
                    ColorDrawable(Color.LTGRAY)
                } else {
                    recipe.imageUrl
                },
                contentDescription = recipe.name,
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.TopCenter)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            // UI 테스트용 플레이스홀더
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.TopCenter)
                    .clip(CircleShape)
                    .background(AppColors.gray3)
            )
        }
    }
}

// preview
@Preview(showBackground = true)
@Composable
private fun HomeRecipeCardPreview() {
    HomeRecipeCard(
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
        isSaved = true,
        onBookmarkClick = {},
        onClick = {},
        enableAnimation = true
    )
}
