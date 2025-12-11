package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun AverageRatingButton(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
) {
    Box(
        modifier = modifier
            .width(37.dp)
            .height(16.dp)
            .background(
                color = AppColors.secondary20,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(vertical = 2.dp, horizontal = 7.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_star_6),
                contentDescription = "average rating",
                tint = AppColors.secondary100,
                modifier = Modifier
                    .size(8.dp)
                    .align(Alignment.CenterVertically),
            )

            Spacer(modifier = Modifier.width(3.25.dp))

            Text(
                text = rating.toString(),
                style = AppTextStyles.smallerTextRegular.copy(fontSize = 8.sp)
            )
        }
    }
}

@Preview
@Composable
private fun AverageRatingButtonPreview() {
    AverageRatingButton()
}
