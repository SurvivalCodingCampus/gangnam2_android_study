package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.recipe.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun HomeRecipeCard(
    recipe: Recipe,
    isSaved: Boolean,
    onBookmarkClick: () -> Unit,
    enableAnimation: Boolean,
) {
    Box(modifier = Modifier.size(width = 150.dp, height = 231.dp)) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(176.dp)
                .background(AppColors.gray4.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
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
                modifier = Modifier.padding(top = 56.dp).fillMaxWidth(),
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
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = if (isSaved) AppColors.primary80 else AppColors.gray3,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        // ⭐ 테스트에서는 이미지/애니메이션 완전 제거
        if (!enableAnimation) {
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

/* ---------------- Preview ---------------- */

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
            imageUrl = "",
            createdAt = System.currentTimeMillis(),
        ),
        isSaved = true,
        onBookmarkClick = {},
        enableAnimation = true
    )
}
