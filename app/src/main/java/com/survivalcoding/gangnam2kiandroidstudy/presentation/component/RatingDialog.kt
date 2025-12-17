package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppTextStyles

@Composable
fun RatingDialog(onChange: (Int) -> Unit) {

    var selectedRatingCount by rememberSaveable { mutableIntStateOf(0) }


    Box(
        modifier = Modifier.size(width = 170.dp, height = 91.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(10.dp))
            Text("Rate recipe", modifier = Modifier, style = AppTextStyles.smallerTextRegular)
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Spacer(modifier = Modifier.width(15.dp))
                Image(
                    painter = painterResource(if (selectedRatingCount > 0) R.drawable.star_6_filled else R.drawable.star_6),
                    contentDescription = "별", modifier = Modifier.clickable{
                        selectedRatingCount = 1
                        onChange(1)
                    }
                )
                Spacer(modifier = Modifier.width(15.dp))
                Image(
                    painter = painterResource(if (selectedRatingCount > 1) R.drawable.star_6_filled else R.drawable.star_6),
                    contentDescription = "별", modifier = Modifier.clickable{
                        selectedRatingCount = 2
                        onChange(2)
                    }
                )
                Spacer(modifier = Modifier.width(15.dp))
                Image(
                    painter = painterResource(if (selectedRatingCount > 2) R.drawable.star_6_filled else R.drawable.star_6),
                    contentDescription = "별", modifier = Modifier.clickable{
                        selectedRatingCount = 3
                        onChange(3)
                    }
                )
                Spacer(modifier = Modifier.width(15.dp))
                Image(
                    painter = painterResource(if (selectedRatingCount > 3) R.drawable.star_6_filled else R.drawable.star_6),
                    contentDescription = "별", modifier = Modifier.clickable{
                        selectedRatingCount = 4
                        onChange(4)
                    }
                )
                Spacer(modifier = Modifier.width(15.dp))
                Image(
                    painter = painterResource(if (selectedRatingCount > 4) R.drawable.star_6_filled else R.drawable.star_6),
                    contentDescription = "별", modifier = Modifier.clickable{
                        selectedRatingCount = 5
                        onChange(5)
                    }
                )
                Spacer(modifier = Modifier.width(15.dp))
            }
            Spacer(
                modifier = Modifier.height(5.dp)
            )
            Box(
                modifier = Modifier
                    .size(width = 61.dp, height = 20.dp)
                    .background(
                        color = if (selectedRatingCount > 0) AppColors.rating else AppColors.gray4,
                        shape = RoundedCornerShape(5.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Send",
                    modifier = Modifier,
                    style = AppTextStyles.smallerTextRegular.copy(
                        fontSize = 8.sp,
                        color = AppColors.white
                    )
                )

            }


        }


    }

}

@Preview(showBackground = true)
@Composable
fun RatingDialogPreview() {
    RatingDialog({})
}