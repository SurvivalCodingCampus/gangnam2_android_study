package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppFonts

@Composable
fun MediumButton(text: String, onClick: () -> Unit) {
    //InteractionSource 생성 (상호작용 감지)
    val interactionSource = remember { MutableInteractionSource() }
    // 2. pressed 상태 추적
    // 현재 버튼이 눌리고 있는지 여부를 State로 관찰
    val isPressed by interactionSource.collectIsPressedAsState()

    Box(
        modifier = Modifier
            .background(color = if(!isPressed)AppColors.primary100 else AppColors.gray4, shape = RoundedCornerShape(10.dp))
            .size(width = 243.dp, height = 54.dp)
            .clickable(onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(width = 114.dp, height = 24.dp), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    fontFamily = AppFonts.poppins,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.white,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.size(9.dp))
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
fun MediumButtonPreview() {
    MediumButton(text = "Button", onClick = {})

}