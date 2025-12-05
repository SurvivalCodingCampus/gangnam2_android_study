package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun TabItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    height: Dp = 33.dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(height = height)
            .background(
                color = if (selected) AppColors.primary100 else Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable() { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(17.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = AppTextStyles.smallTextBold.copy(
                    color = if (selected) AppColors.white else AppColors.primary80
                ),
                fontSize = 11.sp
            )
        }
    }
}
