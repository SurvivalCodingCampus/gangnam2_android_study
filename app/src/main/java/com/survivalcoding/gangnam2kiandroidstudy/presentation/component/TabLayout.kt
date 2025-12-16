package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun TabLayout(
    modifier: Modifier = Modifier,
    currentSelectTabPosition: Int,
    tabs: List<String> = emptyList(),
    onTabClick: (Int) -> Unit
) {
    Row(
        modifier = modifier.wrapContentWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        tabs.forEachIndexed { i, tab ->
            TabButton(
                text = tab,
                modifier = Modifier.weight(1f),
                isSelected = currentSelectTabPosition == i,
                onClick = { onTabClick(i) })
        }
    }
}

@Composable
fun TabButton(
    text: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(
        onClick = onClick,
        modifier = modifier.height(33.dp),
        shape = RoundedCornerShape(10.dp),
        colors = if (isSelected) ButtonColors(
            containerColor = if (isPressed) AppColors.gray4 else AppColors.primary100,
            contentColor = AppColors.white,
            disabledContainerColor = AppColors.gray4,
            disabledContentColor = AppColors.white
        ) else ButtonColors(
            containerColor = AppColors.transparent,
            contentColor = AppColors.transparent,
            disabledContainerColor = AppColors.transparent,
            disabledContentColor = AppColors.transparent
        ),
        interactionSource = interactionSource
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.height(17.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    style = AppTextStyles.smallerTextRegular.copy(
                        color = if (isSelected) AppColors.white else AppColors.primary80,
                        fontWeight = FontWeight.SemiBold
                    ),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TabLayoutPreview() {
    TabLayout(
        tabs = listOf("Label", "Label"),
        currentSelectTabPosition = 0,
        onTabClick = {}
    )
}