package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.survivalcoding.gangnam2kiandroidstudy.R

@Composable
fun RecipeDetailMenu(
    onDismiss: () -> Unit,
    onShare: () -> Unit
) {
    DropdownMenu(
        expanded = true,
        onDismissRequest = onDismiss
    ) {
        DropdownMenuItem(
            text = { Text("Share") },
            onClick = onShare,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_share),
                    contentDescription = null
                )
            }
        )
        DropdownMenuItem(
            text = { Text("Rate Recipe") },
            onClick = { onDismiss() },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.star_5),
                    contentDescription = null
                )
            }
        )
        DropdownMenuItem(
            text = { Text("Review") },
            onClick = { onDismiss() },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_message),
                    contentDescription = null
                )
            }
        )
        DropdownMenuItem(
            text = { Text("Unsave") },
            onClick = { onDismiss() },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.bookmark_fill),
                    contentDescription = null
                )
            }
        )
    }
}