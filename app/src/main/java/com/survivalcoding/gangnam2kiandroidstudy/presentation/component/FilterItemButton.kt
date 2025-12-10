package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun FilterItemButton(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .height(28.dp)
            .clickable { onClick() }
            .border(
                width = 1.dp,
                color = if (isSelected) AppColors.primary100 else AppColors.primary80,
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = if (isSelected) AppColors.primary100 else AppColors.white,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = AppTextStyles.smallerTextBold,
            color = if (isSelected) AppColors.white else AppColors.primary80
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FilterItemButtonPreview() {
    FilterItemButton(text = "Text", isSelected = false)
}

@Preview(showBackground = true)
@Composable
private fun FilterItemButtonPreview2() {
    FilterItemButton(text = "Text", isSelected = true)
}
