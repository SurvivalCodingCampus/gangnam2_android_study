package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun Tabs2(
    labels: List<String>,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    onValueChange: (Int) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(AppColors.gray4),
        verticalAlignment = Alignment.CenterVertically
    ) {
        labels.forEachIndexed { index, label ->
            val selected = selectedIndex == index

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        if (selected) AppColors.primary100 else AppColors.gray4
                    )
                    .clickable { onValueChange(index) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label,
                    style = AppTextStyles.smallerTextBold,
                    color = if (selected) AppColors.white else AppColors.primary80
                )
            }
        }
    }
}
