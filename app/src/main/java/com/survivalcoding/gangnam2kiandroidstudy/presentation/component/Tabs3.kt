package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun Tabs3(
    labels: List<String>,
    selectedIndex: Int,
    onValueChange: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .size(width = 375.dp, height = 58.dp)
            .background(color = AppColors.white),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        labels.forEachIndexed { index, label ->
            val isSelected = selectedIndex == index
            Box(
                modifier = Modifier
                    .size(width = 107.dp, height = 33.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { onValueChange(index) }
                    .background(
                        color = if (isSelected) AppColors.primary100 else AppColors.white,
                        shape = RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.size(width = 83.dp, height = 17.dp),
                    text = label,
                    style = AppTextStyles.smallerTextBold.copy(
                        color = if (isSelected) AppColors.white else AppColors.primary80
                    ),
                    textAlign = TextAlign.Center
                )
            }
            if (index < labels.size - 1) {
                Spacer(modifier = Modifier.width(7.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Tabs3Preview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Tabs3(
            labels = listOf("Label1", "Label2","Label3"),
            selectedIndex = 0,
        ) {

        }
    }
}