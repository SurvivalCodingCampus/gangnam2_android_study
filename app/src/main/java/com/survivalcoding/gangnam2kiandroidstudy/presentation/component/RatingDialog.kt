package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RatingDialog(
    modifier: Modifier = Modifier,
    title: String,
    actionName: String = "Send",
    onChange: (Int) -> Unit = {},
    onClick: (Int) -> Unit = {},
) {
    val selectedRating = remember { mutableStateOf(0) }

    Dialog(onDismissRequest = {}) {
        Card(
            modifier = Modifier
                .size(170.dp, 91.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = title, style = AppTextStyles.smallerTextRegular)
                Row(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)) {
                    repeat(5) { index ->
                        val isFilled = index < selectedRating.value

                        Icon(
                            painter = painterResource(if (isFilled) R.drawable.star_1 else R.drawable.unfill_star),
                            contentDescription = "star icon ${index + 1}",
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    selectedRating.value = index + 1
                                    onChange(selectedRating.value)
                                },
                            tint = AppColors.rating
                        )

                        if (index != 4) {
                            Spacer(Modifier.width(10.dp))
                        }
                    }
                }

                Row(
                    modifier = modifier
                        .size(61.dp, 20.dp)
                        .background(
                            color = if (selectedRating.value > 0) AppColors.rating else AppColors.gray4,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable {
                            if (selectedRating.value != 0) {
                                onClick(selectedRating.value)
                            }
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = actionName, style = AppTextStyles.smallerTextRegular, color = AppColors.white)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RatingDialogPreview() {
    RatingDialog(title = "Rate recipe")
}
