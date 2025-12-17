package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppTextStyles

@Composable
fun FilterButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) AppColors.primary100 else AppColors.white
    val borderColor = if (isSelected) AppColors.primary100 else AppColors.primary80
    val textColor = if (isSelected) AppColors.white else AppColors.primary80

    Box(
        modifier = Modifier
            .height(height = 27.dp)
            .clickable(onClick = onClick)
            .background(color = backgroundColor, shape = RoundedCornerShape(10.dp))
            .border(
                color = borderColor,
                width = 1.dp,
                shape = RoundedCornerShape(10.dp)
            ),

        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = text,
                style = AppTextStyles.smallerTextRegular.copy(color = textColor)
            )

        }
    }

}


@Preview
@Composable
fun FilterButtonPreview() {
    Column {

        FilterButton("Text", true, {})
        Spacer(modifier = Modifier.height(30.dp))
        FilterButton("Text", false, {})
    }
}