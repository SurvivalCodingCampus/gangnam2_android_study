package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors

@Composable
fun BookMarkButton(
    isBookmarked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .background(
                color = AppColors.white,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(
                if (isBookmarked)
                    R.drawable.ic_bookmark
                else
                    R.drawable.ic_bookmark_outline
            ),
            contentDescription = "Bookmark",
            tint = AppColors.primary80,
            modifier = Modifier.size(16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun BookMarkButtonPreview() {
    BookMarkButton(
        isBookmarked = true,
        onClick = {}
    )
}

