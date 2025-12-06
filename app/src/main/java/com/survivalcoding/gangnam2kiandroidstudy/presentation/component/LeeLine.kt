package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun LeeLine(
    width: Dp = 50.dp,
    color: Color = AppColors.gray4,
    thickness: Dp = 1.dp
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(thickness) // 라인 두께
            .background(color)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LeeLine()
}
