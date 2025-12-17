package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppTextStyles

@Composable
fun SmallButton2(text: String, isSelected: Boolean, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .height(height = 37.dp)
            .background(
                color = if (isSelected) AppColors.primary100 else AppColors.white,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(
                onClick = {
                    if (!isSelected) {
                        onClick(text)
                    } //toggle은 안되게
                },

                ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .height(height = 17.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 30.dp),
                text = text,
                color = if (isSelected) AppColors.white else AppColors.primary80,
                style = AppTextStyles.smallerTextBold.copy(fontWeight = FontWeight.SemiBold),
                fontWeight = FontWeight.SemiBold
            )
        }
    }


}

@Composable
@Preview
fun SmallButton2Preview() {
    //SmallButton2(text = "Button", onClick = {})
}