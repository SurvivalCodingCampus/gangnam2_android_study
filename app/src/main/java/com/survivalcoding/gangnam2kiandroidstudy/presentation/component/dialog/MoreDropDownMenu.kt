package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun MoreDropdownMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    onShareClick: () -> Unit,
    onRateClick: () -> Unit,
    onReviewClick: () -> Unit,
    onUnsaveClick: () -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        modifier = Modifier
            .width(164.dp)
            .background(AppColors.white, RoundedCornerShape(8.dp))
    ) {
        MoreMenuItem(
            iconRes = R.drawable.ic_more_share,
            text = "Share",
            onClick = onShareClick
        )
        MoreMenuItem(
            iconRes = R.drawable.ic_more_star,
            text = "Rate Recipe",
            onClick = onRateClick
        )
        MoreMenuItem(
            iconRes = R.drawable.ic_more_review,
            text = "Review",
            onClick = onReviewClick
        )
        MoreMenuItem(
            iconRes = R.drawable.ic_more_unsave,
            text = "Unsave",
            onClick = onUnsaveClick
        )
    }
}

@Composable
private fun MoreMenuItem(
    iconRes: Int,
    text: String,
    onClick: () -> Unit,
) {
    DropdownMenuItem(
        text = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = text,
                    style = AppTextStyles.smallTextRegular
                )
            }
        },
        onClick = onClick,
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
    )
}


@Preview(showBackground = true)
@Composable
private fun MoreDropdownMenuWithIconPreview() {
    Box(
        modifier = Modifier.padding(24.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_more_outline),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        MoreDropdownMenu(
            expanded = true,
            onDismiss = {},
            onShareClick = {},
            onRateClick = {},
            onReviewClick = {},
            onUnsaveClick = {},
        )
    }
}
