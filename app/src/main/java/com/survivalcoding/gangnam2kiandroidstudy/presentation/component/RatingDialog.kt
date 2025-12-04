package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import android.R.attr.contentDescription
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RatingDialog(
    title: String,
    actionName: String,
    onChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val rateCount = remember { mutableStateOf(0) }

    val isPossible = rateCount.value > 0

    val buttonColor = if (isPossible) AppColors.rating else AppColors.gray4


    AlertDialog(
        containerColor = AppColors.white,
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    style = AppTextStyles.smallerTextRegular,
                )
            }
        },
        modifier = Modifier.clip(RoundedCornerShape(8.dp)),
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(5) { index ->
                        val starNumber = index + 1
                        val isFilled = rateCount.value >= starNumber

                        Icon(
                            imageVector =  if (isFilled) Icons.Filled.Star else Icons.Outlined.StarBorder,
                            contentDescription = "star_$index",
                            tint = AppColors.rating,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    rateCount.value = starNumber
                                }
                        )

                        if (index < 4)
                            Spacer(modifier = Modifier.width(10.dp))
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Box(
                    modifier = Modifier
                        .width(61.dp)
                        .height(20.dp)
                        .background(color = buttonColor, shape = RoundedCornerShape(6.dp))
                        .clickable {
                            if (isPossible) {
                                onChange(rateCount.value)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = actionName,
                        style = AppTextStyles.smallerTextRegular.copy(
                            fontSize = 8.sp,
                            color = AppColors.white
                        )
                    )
                }
            }
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

            }
        },
        onDismissRequest = { },
        dismissButton = {

        }


    )
}

@Preview
@Composable
private fun RatingDialogPrev() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        RatingDialog(
            title = "Rate recipe",
            actionName = "Rate",
            onChange = {
                Log.d("TAG", "RatingDialogPrev: $it")
            }
        )
    }
}