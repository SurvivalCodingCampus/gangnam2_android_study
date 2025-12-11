package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun SettingButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clickable(onClick = onClick)
            .background(color = AppColors.primary100, shape = RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(R.drawable.outline_setting_4),
            tint = AppColors.white,
            contentDescription = "필터설정"
        )
    }
}

@Preview
@Composable
fun SettingButtonPreview() {
    Column {
        SettingButton({})
    }
}