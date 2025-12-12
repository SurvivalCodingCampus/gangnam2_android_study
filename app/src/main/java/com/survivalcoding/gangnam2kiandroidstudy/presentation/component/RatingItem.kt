package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun RatingItem(rating: Int, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        repeat(5) { index ->
            val isFilled = index < rating

            Box(modifier = Modifier
                .size(11.16.dp, 12.dp)
                .padding(end = 1.86.dp)
            ) {
                Icon(
                    painter = painterResource(if (isFilled) R.drawable.star_1 else R.drawable.unfill_star),
                    contentDescription = "star icon ${index + 1}",
                    modifier = Modifier
                        .size(10.46.dp, 10.5.dp),
                    tint = AppColors.rating
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RatingItemPreview() {
    Scaffold { innerPadding ->
        RatingItem(
            rating = 4,
            modifier = Modifier.padding(innerPadding)
        )
    }
}