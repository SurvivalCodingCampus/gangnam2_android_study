package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun RateDialog(
    modifier: Modifier = Modifier,
    title: String,
    actionName: String,
    onChange: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    var selectedRating by rememberSaveable { mutableStateOf(0) }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = modifier
                .padding(horizontal = 82.dp)
                .height(91.dp)
                .background(AppColors.white, RoundedCornerShape(8.dp))
                .padding(horizontal = 15.dp, vertical = 10.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                // Title
                Text(
                    text = title,
                    style = AppTextStyles.smallerTextRegular
                )

                // Stars
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.size(140.dp, 24.dp)
                ) {
                    (1..5).forEach { value ->
                        Icon(
                            painter = painterResource(
                                if (selectedRating >= value)
                                    R.drawable.ic_star_6 // 채워진 별
                                else
                                    R.drawable.ic_star_6_1 // 빈 별
                            ),
                            contentDescription = "$value stars",
                            tint = if (selectedRating >= value)
                                AppColors.secondary100
                            else
                                Color.Unspecified,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    selectedRating = if (selectedRating == value) {
                                        (value - 1).coerceAtLeast(0)  // 같은 별 클릭 선택 해제
                                    } else {
                                        value // 다른 별 선택 선택 변경
                                    }
                                }
                        )
                    }
                }

                // Action Button
                SmallButton(
                    text = actionName,
                    enabled = selectedRating > 0,
                    shape = 6.dp,
                    textStyle = AppTextStyles.smallerTextRegular.copy(
                        color = AppColors.white,
                        fontSize = 8.sp
                    ),
                    modifier = Modifier
                        .size(61.dp, 20.dp)
                ) {
                    onChange(selectedRating)
                }
            }
        }
    }
}