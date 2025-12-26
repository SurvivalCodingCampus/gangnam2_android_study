package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import androidx.compose.ui.window.Dialog
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun MoreDialog(
    onDismiss: () -> Unit,
    onShareClick: () -> Unit,
    onRateClick: () -> Unit,
    onReviewClick: () -> Unit,
    onUnsaveClick: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            color = AppColors.white,
            shape = RoundedCornerShape(8.dp),
            tonalElevation = 6.dp,
        ) {
            Column(
                modifier = Modifier
            ) {
                MoreDialogItem(
                    iconRes = R.drawable.ic_more_share,
                    text = "Share",
                    onClick = onShareClick
                )
                MoreDialogItem(
                    iconRes = R.drawable.ic_more_star,
                    text = "Rate Recipe",
                    onClick = onRateClick
                )
                MoreDialogItem(
                    iconRes = R.drawable.ic_more_review,
                    text = "Review",
                    onClick = onReviewClick
                )
                MoreDialogItem(
                    iconRes = R.drawable.ic_more_unsave,
                    text = "Unsave",
                    onClick = onUnsaveClick
                )
            }
        }
    }
}

@Composable
private fun MoreDialogItem(
    iconRes: Int,
    text: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(width = 144.dp, height = 40.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = text,
                style = AppTextStyles.smallTextRegular
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun MoreDialogPreview() {
    MoreDialog(
        onDismiss = {},
        onShareClick = {},
        onRateClick = {},
        onReviewClick = {},
        onUnsaveClick = {},
    )
}