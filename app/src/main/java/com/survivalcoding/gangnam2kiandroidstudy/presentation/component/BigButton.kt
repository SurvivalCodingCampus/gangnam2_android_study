package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppFonts

@Composable
fun BigButton(onClick: () -> Unit, text: String) {
    //InteractionSource 생성 (상호작용 감지)
    val interactionSource = remember { MutableInteractionSource() }
    // 2. pressed 상태 추적
    // 현재 버튼이 눌리고 있는지 여부를 State로 관찰
    val isPressed by interactionSource.collectIsPressedAsState()

    Box(
        modifier = Modifier
            .height(height = 60.dp)
            .fillMaxWidth()
            .background(
                color = if (isPressed) AppColors.gray4 else AppColors.primary100,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null, //이걸 해야 둥근 모서리 그대로 유지됨
                onClick = onClick
            ), contentAlignment = Alignment.Center
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

@Preview(showBackground = true)
@Composable
fun BigButtonPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BigButton(onClick = {}, text = "Button")
    }
}
