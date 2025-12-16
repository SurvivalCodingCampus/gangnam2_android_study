package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.ingredient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun IngredientSummaryRow(
    serveCount: Int = 1,
    rightLabel: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 22.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Serve
        Row(
            modifier = Modifier.height(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_serve_outline),
                contentDescription = null,
                tint = AppColors.gray3,
                modifier = Modifier.size(17.dp)
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = "$serveCount serve",
                style = AppTextStyles.smallerTextRegular.copy(
                    color = AppColors.gray3
                )
            )
        }

        // Right text (Items / Steps)
        Text(
            text = rightLabel,
            style = AppTextStyles.smallerTextRegular.copy(
                color = AppColors.gray3
            )
        )
    }
}
