package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun FilterSettingButton(modifier: Modifier = Modifier, onBottomSheetClick: () -> Unit = {}) {
    Box(
        modifier = modifier
            .size(40.dp)
            .background(color = AppColors.primary100, shape = RoundedCornerShape(10.dp))
            .clickable { onBottomSheetClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.setting_4),
            contentDescription = "Filter Setting",
            tint = AppColors.white,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FilterSettingButtonPreview() {
    Scaffold { innerPadding ->
        FilterSettingButton(modifier = Modifier.padding(innerPadding))
    }
}