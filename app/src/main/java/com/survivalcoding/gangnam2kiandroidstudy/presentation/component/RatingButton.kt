package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RatingButton(
    text: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(10.dp)
    val color = if (isSelected) AppColors.White else AppColors.Primary80
    val backgroundColor = if (isSelected) AppColors.Primary100 else AppColors.White
    val borderColor = if (isSelected) AppColors.Primary100 else AppColors.Primary80

    Box(
        modifier = modifier
            .height(28.dp)
            .clip(shape)
            .clickable(onClick = onClick)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = shape,
            )
            .background(
                color = backgroundColor,
                shape = shape,
            ),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                style = AppTextStyles.PoppinsSmallerRegular.copy(color = color),
            )
            Image(
                painter = painterResource(R.drawable.bold_star),
                contentDescription = "star",
                colorFilter = ColorFilter.tint(color = color),
                modifier = Modifier.size(18.dp),
            )
        }
    }
}

@Preview
@Composable
fun RatingButtonPreview() {
    RatingButton(text = "5", isSelected = false, onClick = {})
}

@Preview
@Composable
fun RatingButtonSelectedPreview() {
    RatingButton(text = "5", isSelected = true, onClick = {})
}
