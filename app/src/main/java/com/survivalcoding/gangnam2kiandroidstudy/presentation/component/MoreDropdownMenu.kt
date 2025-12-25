package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun MoreDropdownMenu(
    expanded: Boolean = false,
    onDismissRequest: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {

        DropdownMenuItem(
            text = {
                Text(
                    text = "share",
                    style = AppTextStyles.smallTextRegular,
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.bold_share),
                    contentDescription = "공유 아이콘",
                    modifier = Modifier.size(20.dp),
                )
            },
            onClick = { /* Do something... */ }
        )

        DropdownMenuItem(
            text = {
                Text(
                    text = "Rate Recipe",
                    style = AppTextStyles.smallTextRegular,
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.bold_star),
                    contentDescription = "별점 아이콘",
                    modifier = Modifier.size(20.dp),
                )
            },
            onClick = { /* Do something... */ }
        )

        DropdownMenuItem(
            text = {
                Text(
                    text = "Review",
                    style = AppTextStyles.smallTextRegular,
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.bold_message),
                    contentDescription = "리뷰 아이콘",
                    modifier = Modifier.size(20.dp),
                )
            },
            onClick = { /* Do something... */ }
        )

        DropdownMenuItem(
            text = {
                Text(
                    text = "Unsave",
                    style = AppTextStyles.smallTextRegular,
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.bold_share),
                    contentDescription = "북마크 취소 아이콘",
                    modifier = Modifier.size(20.dp),
                )
            },
            onClick = { /* Do something... */ }
        )
    }
}


@Preview
@Composable
private fun PreviewMoreDropdownMenu() {
    MoreDropdownMenu(
        expanded = true,
    )
}