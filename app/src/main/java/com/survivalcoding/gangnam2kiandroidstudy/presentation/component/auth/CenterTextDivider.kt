package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun CenterTextDivider(text: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 20.dp, horizontal = 60.dp)
            .height(17.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = AppColors.gray4
        )

        Text(
            modifier = Modifier.padding(horizontal = 7.dp),
            text = text,
            style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray4)
        )

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = AppColors.gray4
        )
    }
}
