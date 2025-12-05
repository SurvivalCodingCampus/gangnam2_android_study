package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun SmallButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Dp = 10.dp,
    textStyle: TextStyle = AppTextStyles.smallerTextBold.copy(color = AppColors.white),
    onClick: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor =
        when {
            !enabled -> AppColors.gray4 // 진짜 비활성
            isPressed -> AppColors.gray4    // 누르고 있는 동안만 Disable
            else -> AppColors.primary100    // 나머지는 기본 색
        }

    Box(
        modifier = modifier
            .defaultMinSize(minWidth = 59.dp, minHeight = 20.dp)// 최소 규격
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(shape),
            )
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
            ) {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.size(width = 114.dp, height = 17.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    style = textStyle,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SmallButtonPreview() {
    Column {
        SmallButton("Send", modifier = Modifier.size(59.dp, 35.dp))
        Spacer(Modifier.size(10.dp))
        SmallButton("Disabled", modifier = Modifier.size(85.dp, 43.dp), enabled = false)
    }
}