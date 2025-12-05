package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
fun FilterButton(
    value: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val background = if (isSelected) AppColors.primary100 else AppColors.white
    val borderColor = if (isSelected) AppColors.primary100 else AppColors.primary80
    val textColor = if (isSelected) AppColors.white else AppColors.primary80

    Row(
        modifier = modifier
            .background(background, shape = RoundedCornerShape(10.dp))
            .border(1.dp, borderColor, RoundedCornerShape(10.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = value,
            color = textColor,
            fontWeight = FontWeight.Medium,
            style = AppTextStyles.smallerTextRegular
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilterButtonPreview() {
    FilterButton("Text", false)
}