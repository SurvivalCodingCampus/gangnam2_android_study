package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SmallButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(width = 174.dp, height = 37.dp)
            .background(color = AppColors.primary100, shape = RoundedCornerShape(10.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = 114.dp, height = 17.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = AppColors.white, style = AppTextStyles.smallerTextBold)
        }
    }


}

@Composable
@Preview
fun SmallButtonPreview() {
    SmallButton(text = "Button", onClick = {})
}