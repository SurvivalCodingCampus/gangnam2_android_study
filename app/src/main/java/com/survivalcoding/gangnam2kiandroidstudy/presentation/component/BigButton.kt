package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppFonts

@Composable
fun BigButton(onClick: () -> Unit, text: String) {
    Box(
        modifier = Modifier
            .size(width = 315.dp, height = 60.dp)
            .background(color = AppColors.primary100, shape = RoundedCornerShape(10.dp))
            .clickable(onClick = onClick), contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.size(width = 114.dp, height = 24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontFamily = AppFonts.poppins,
                fontWeight = FontWeight.Bold,
                color = AppColors.white,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.width(11.dp))
            Icon(
                Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "화살표",
                tint = AppColors.white
            )
        }
    }
}

@Preview
@Composable
fun BigButtonPreview() {
    BigButton(onClick = {}, text = "Button")
}