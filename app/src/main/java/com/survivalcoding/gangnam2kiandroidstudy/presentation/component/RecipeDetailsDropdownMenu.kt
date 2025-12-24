package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RecipeDetailsDropdownMenu(
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
    isSaved: Boolean = false,
    onDismissRequest: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onRateClick: () -> Unit = {},
    onReviewClick: () -> Unit = {},
    onBookmarkClick: () -> Unit = {},
) {
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier
            .width(164.dp)
            .height(180.dp)
            .background(
                color = AppColors.White,
                shape = RoundedCornerShape(10.dp),
            ),
    ) {
        DropdownMenuItem(
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.bold_share),
                    contentDescription = "share icon",
                    modifier = Modifier.size(20.dp),
                )
            },
            text = {
                Text(
                    text = "share",
                    style = AppTextStyles.PoppinsSmallRegular,
                )
            },
            onClick = onShareClick,
            modifier = Modifier.height(40.dp),
        )
        DropdownMenuItem(
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.bold_star),
                    contentDescription = "rate recipe icon",
                    modifier = Modifier.size(20.dp),
                )
            },
            text = {
                Text(
                    text = "Rate Recipe",
                    style = AppTextStyles.PoppinsSmallRegular,
                )
            },
            onClick = onRateClick,
            modifier = Modifier.height(40.dp),
        )
        DropdownMenuItem(
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.bold_message),
                    contentDescription = "review icon",
                    modifier = Modifier.size(20.dp),
                )
            },
            text = {
                Text(
                    text = "Review",
                    style = AppTextStyles.PoppinsSmallRegular,
                )
            },
            onClick = onReviewClick,
            modifier = Modifier.height(40.dp),
        )
        DropdownMenuItem(
            leadingIcon = {
                Icon(
                    painter = painterResource(if (isSaved) R.drawable.bold_bookmark else R.drawable.outline_bookmark),
                    contentDescription = "bookmark icon",
                    modifier = Modifier.size(20.dp),
                )
            },
            text = {
                Text(
                    text = if (isSaved) "Unsave" else "Save",
                    style = AppTextStyles.PoppinsSmallRegular,
                )
            },
            onClick = onBookmarkClick,
            modifier = Modifier.height(40.dp),
        )
    }
}

@Preview(showBackground = true, widthDp = 164, heightDp = 180)
@Composable
private fun RecipeDetailsDropdownMenuPreview() {
    RecipeDetailsDropdownMenu(
        isExpanded = true,
        isSaved = true,
    )
}