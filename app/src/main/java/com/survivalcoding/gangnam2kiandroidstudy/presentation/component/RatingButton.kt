package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RatingButton(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .height(28.dp)
            .background(
                if (isSelected) AppColors.primary100 else AppColors.white,
                RoundedCornerShape(10.dp)
            )
            .border(
                width = if (!isSelected) 1.dp else 0.dp,
                color = if (!isSelected) AppColors.primary80 else AppColors.white,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = AppTextStyles.smallerTextRegular.copy(
                    if (isSelected) AppColors.white else AppColors.primary80
                )
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Star",
                tint = if (isSelected) AppColors.white else AppColors.primary80
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun RatingButtonPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        RatingButton("5", true)
        RatingButton("5", false)
    }

}