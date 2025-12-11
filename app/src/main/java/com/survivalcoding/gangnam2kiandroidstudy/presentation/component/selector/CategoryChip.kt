package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.selector

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun CategoryChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor =
        if (isSelected) AppColors.primary100 else AppColors.white

    val textColor =
        if (isSelected) AppColors.white else AppColors.primary100

    Box(
        modifier = modifier
            .height(31.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = AppTextStyles.smallerTextBold.copy(color = textColor)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryChipPreview() {
    var selected by rememberSaveable { mutableStateOf(false) }

    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        CategoryChip(
            text = "Indian",
            isSelected = selected,
            onClick = { selected = !selected }
        )

        CategoryChip(
            text = "Italian",
            isSelected = !selected,
            onClick = { selected = !selected }
        )
    }
}



