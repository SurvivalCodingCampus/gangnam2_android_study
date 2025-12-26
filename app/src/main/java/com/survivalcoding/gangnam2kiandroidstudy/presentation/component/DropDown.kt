package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun DropDown(
    expanded: Boolean,
    onDismiss: () -> Unit = {},
    onShare: () -> Unit = {}
) {
    DropdownMenu(
        modifier = Modifier.padding(10.dp).clip(RoundedCornerShape(8.dp)),
        expanded = expanded,
        containerColor = AppColors.white,
        onDismissRequest = { onDismiss() }
    ) {
        DropdownMenuItem(
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.bold_share),
                    contentDescription = "share",
                    tint = AppColors.black2
                )
            },
            text = {
                Text(
                    "share",
                    style = AppTextStyles.smallTextRegular.copy(color = AppColors.black2)
                )
            },
            onClick = {
                onShare()
            }
        )
        DropdownMenuItem(
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.bold_star),
                    contentDescription = "rate recipe",
                    tint = AppColors.black2
                )
            },
            text = {
                Text(
                    "Rate Recipe",
                    style = AppTextStyles.smallTextRegular.copy(color = AppColors.black2)
                )
            },
            onClick = {

            }
        )
        DropdownMenuItem(
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.bold_message),
                    contentDescription = "review",
                    tint = AppColors.black2
                )
            },
            text = {
                Text(
                    "Review",
                    style = AppTextStyles.smallTextRegular.copy(color = AppColors.black2)
                )
            },
            onClick = {

            }
        )
        DropdownMenuItem(
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.bold_bookmark_active),
                    contentDescription = "unsave",
                    tint = AppColors.black2
                )
            },
            text = {
                Text(
                    "Unsave",
                    style = AppTextStyles.smallTextRegular.copy(color = AppColors.black2)
                )
            },
            onClick = {

            }
        )

    }

}

@Preview(showBackground = true)
@Composable
private fun DropDownPrev() {

        DropDown(expanded = true)

}