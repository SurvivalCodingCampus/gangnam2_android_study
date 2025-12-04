package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun Tabs(
    labels: List<String>,
    selectedIndex: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier.size(804.dp, 98.dp),
) {
    Row(
        modifier = modifier
            .size(375.dp, 58.dp)
            .background(AppColors.white),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        labels.forEachIndexed { index, label ->
            val isSelected = index == selectedIndex
            Box(
                modifier = Modifier
                    .size(107.dp, 33.dp)
                    .background(
                        color = if (isSelected) AppColors.primary100 else AppColors.white,
                        shape = RoundedCornerShape(10.dp),
                    )
                    .clickable { onValueChange(index) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label,
                    style = AppTextStyles.smallTextBold,
                    color = if (isSelected) AppColors.white else AppColors.primary100
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Preview
@Composable
fun TabsPreview() {
    Column {
        Tabs(
            labels = listOf("Label", "Label"),
            selectedIndex = 1,
            onValueChange = {},
        )

        Spacer(modifier = Modifier.height(16.dp))

        Tabs(
            labels = listOf("Label", "Label"),
            selectedIndex = 0,
            onValueChange = {},
        )
    }
}