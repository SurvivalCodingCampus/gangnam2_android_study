package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

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
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
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
    Dialog(
        onDismissRequest = { }
    ) {
        Card(
            modifier = modifier.width(170.dp),
            colors = CardDefaults.cardColors(
                containerColor = AppColors.white
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(10.dp))

                Text(
                    text = title,
                    style = AppTextStyles.smallTextRegular
                )

                Spacer(Modifier.height(5.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    repeat(5) { index ->
                        val starNumber = index + 1
                        val isFilled = rateCount.value >= starNumber

                        Icon(
                            imageVector = if (isFilled) Icons.Filled.Star else Icons.Outlined.StarBorder,
                            contentDescription = "star_$index",
                            tint = AppColors.rating,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    rateCount.value = starNumber
                                }
                        )
                    }
                }

                Spacer(Modifier.height(5.dp))

                Box(
                    modifier = Modifier
                        .background(
                            color = if (isPossible) AppColors.rating else AppColors.gray4,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable(enabled = isPossible) {
                            onChange(rateCount.value)
                        }
                        .padding(vertical = 4.dp, horizontal = 20.dp)
                ) {
                    Text(
                        text = actionName,
                        style = AppTextStyles.smallTextRegular.copy(
                            fontSize = 8.sp,
                            color = AppColors.white
                        )
                    )
                }

                Spacer(Modifier.height(10.dp))
            }
        }
    }
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
            actionName = "Send",
            onChange = {
                Log.d("TAG", "RatingDialogPrev: $it")
            }
        )
    }
}