package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun Tab2(labels: List<String>, selectedIndex: Int, onValueChange: (Int) -> Unit) {
    require(labels.size == 2) { "Tab2는 2개의 label만 필요합니다." }
    Box(
        modifier = Modifier
            .size(width = 375.dp, height = 58.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(modifier = Modifier) {
            Box(
                modifier = Modifier
                    .size(width = 150.dp, height = 33.dp)
                    .background(
                        if (selectedIndex == 0) AppColors.primary100 else AppColors.white,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable { onValueChange(0) },
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    modifier = Modifier,
                    style = AppTextStyles.smallerTextBold.copy(color = if (selectedIndex == 0) AppColors.white else AppColors.primary80),
                    text = labels[0]
                )

            }
            Spacer(modifier = Modifier.width(15.dp))
            Box(
                modifier = Modifier
                    .size(width = 150.dp, height = 33.dp)
                    .background(
                        color = if (selectedIndex == 1) AppColors.primary100 else AppColors.white,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable { onValueChange(1) },
                contentAlignment = Alignment.Center,

                ) {
                Text(
                    text = labels[1],
                    style = AppTextStyles.smallerTextBold.copy(if (selectedIndex == 1) AppColors.white else AppColors.primary80)
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun Tab2Preview() {
    Column {
        Tab2(listOf("Label1", "Label2"), 0, {})
        Tab2(listOf("Label1", "Label2"), 1, {})
    }
}