package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RatingButton(
    rating: Int,
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
            text = rating.toString(),
            color = textColor,
            fontWeight = FontWeight.Medium,
            style = AppTextStyles.smallerTextRegular
        )
        Spacer(modifier = Modifier.width(5.dp))
        Icon(
            painter = painterResource(R.drawable.star_5),
            contentDescription = null,
            tint = textColor,
            modifier = Modifier.size(18.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RatingButtonPreview() {
    RatingButton(4, false)
}