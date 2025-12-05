package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun CustomCheckbox(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit = {},
) {
    Box(
        modifier = modifier
            .size(17.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(
                color = if (isChecked) AppColors.secondary40 else AppColors.white,
            )
            .border(
                width = 1.dp,
                color = AppColors.secondary100,
                shape = RoundedCornerShape(5.dp)
            )
            .clickable { onCheckedChange(!isChecked) },
        contentAlignment = Alignment.Center
    ) {
//        if (isChecked) {
//            Icon(
//                imageVector = Icons.Default.Check,
//                contentDescription = "체크 아이콘",
//                tint = AppColors.white,
//                modifier = Modifier.size(16.dp)
//            )
//        }
    }
}

@Preview
@Composable
private fun PreviewCustomCheckbox() {
    Row {
        CustomCheckbox(true)
        Spacer(Modifier.width(16.dp))
        CustomCheckbox(false)
    }
}
