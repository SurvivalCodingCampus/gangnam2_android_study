package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun FilterTabButton(
    value: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val background = if (isSelected) AppColors.primary100 else AppColors.white
    val textColor = if (isSelected) AppColors.white else AppColors.primary80

    Row(
        modifier = modifier
            .background(background, shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 20.dp, vertical = 7.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = value,
            style = AppTextStyles.smallerTextRegular.copy(
                color = textColor,
                fontWeight = FontWeight.SemiBold
            ),
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilterTabButtonPreview() {
    FilterTabButton("Text", true) {}
}