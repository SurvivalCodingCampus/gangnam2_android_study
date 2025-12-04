package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun BigButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick,
        modifier = modifier.size(width = 315.dp, height = 60.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonColors(
            containerColor = AppColors.primary100,
            contentColor = Color.White,
            disabledContainerColor = Color(0xFFD9D9D9),
            disabledContentColor = Color.White
        )
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
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    style = AppTextStyles.normalTextRegular.copy(color = AppColors.white),
                )
            }
            Spacer(modifier = Modifier.width(11.dp))
            Icon(
                painter = painterResource(R.drawable.arrow_right),
                tint = AppColors.white,
                contentDescription = "오른쪽 화살표"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BigButtonPreview() {
    BigButton("Button")
}
