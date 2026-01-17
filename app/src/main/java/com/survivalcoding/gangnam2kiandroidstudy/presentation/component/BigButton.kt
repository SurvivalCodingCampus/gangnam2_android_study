package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun BigButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val state: MutableState<Int> = remember { mutableIntStateOf(1) }
    state.value = 10

    Box(
        modifier = modifier
            .height(60.dp)
            .background(
                color = Color(0xFF129575),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.size(width = 114.dp, height = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    style = AppTextStyles.normalTextBold.copy(color = AppColors.white),
                )
            }
            Spacer(modifier = Modifier.width(11.dp))
//            Icon(
//                painter = painterResource(R.drawable.arrow_forward_24px),
//                tint = AppColors.white,
//                contentDescription = "오른쪽 화살표"
//            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                modifier = Modifier.size(20.dp),
                tint = AppColors.white,
                contentDescription = "오른쪽 화살표"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BigButtonPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        BigButton(
            text = "Button",
            modifier = Modifier.padding(horizontal = 30.dp),
        )
    }
}
