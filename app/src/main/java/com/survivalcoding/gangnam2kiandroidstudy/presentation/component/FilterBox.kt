package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun FilterBox(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clickable {
                onClick()
            }
            .background(color = AppColors.primary100, shape = RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.outline_setting_4),
            contentDescription = "setting_4",
            modifier = Modifier.size(24.dp),
            tint = AppColors.white
        )
    }
}