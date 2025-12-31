package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun RecipeDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier.width(164.dp),
        shape = RoundedCornerShape(8.dp),
        containerColor = AppColors.white,
    ) {

        // Share
        DropdownMenuItem(
            text = {
                Text(
                    text = "Share",
                    style = AppTextStyles.smallTextRegular,
                    color = AppColors.gray1
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_share),
                    contentDescription = "Share"
                )
            },
            onClick = onShareClick
        )

        // Rate (UI만, 기능은 나중에)
        DropdownMenuItem(
            text = {
                Text(
                    text = "Rate Recipe",
                    style = AppTextStyles.smallTextRegular,
                    color = AppColors.gray1
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.bold_star),
                    contentDescription = "Rate"
                )
            },
            onClick = {}
        )

        // Review
        DropdownMenuItem(
            text = {
                Text(
                    text = "Review",
                    style = AppTextStyles.smallTextRegular,
                    color = AppColors.gray1
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.bold_message),
                    contentDescription = "Review"
                )
            },
            onClick = {}
        )
    }
}
