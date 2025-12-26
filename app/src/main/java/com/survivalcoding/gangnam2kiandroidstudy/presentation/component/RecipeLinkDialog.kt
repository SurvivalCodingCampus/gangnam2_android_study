package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

import androidx.compose.ui.window.Dialog

@Composable
fun RecipeLinkDialog(
    modifier: Modifier = Modifier,
    linkText: String = "",
    onDismissRequest: () -> Unit = {},
    onCopyClick: () -> Unit = {},
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            modifier = Modifier
                .height(167.dp)
                .width(310.dp)
                .background(
                    color = AppColors.white,
                    shape = RoundedCornerShape(20.dp),
                )
        ) {
            Icon(
                painter = painterResource(R.drawable.outline_union),
                contentDescription = "엑스 아이콘",
                modifier = Modifier
                    .padding(15.dp)
                    .size(5.dp)
                    .rotate(45f)
                    .align(Alignment.TopEnd)
                    .clickable { onDismissRequest() }
            )

            Column(
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 15.dp)
            ) {
                Text(
                    text = "Recipe Link",
                    style = AppTextStyles.largeTextBold,
                )
                Text(
                    text = "Copy recipe link and share your recipe link with  friends and family.",
                    style = AppTextStyles.smallerTextRegular,
                    color = AppColors.gray2,
                    modifier = Modifier.padding(vertical = 10.dp),
                )

                Box(
                    modifier = Modifier
                        .height(43.dp)
                        .fillMaxWidth()
                        .background(
                            color = AppColors.gray4.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(9.dp),
                        )
                ) {
                    Text(
                        text = linkText,
                        style = AppTextStyles.smallerTextSemiBold,
                        modifier = Modifier.padding(14.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(85.dp)
                            .background(
                                color = AppColors.primary100,
                                shape = RoundedCornerShape(9.dp),
                            )
                            .align(Alignment.CenterEnd)
                            .clickable { onCopyClick() },
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "Copy Link",
                            style = AppTextStyles.smallerTextBold,
                            color = AppColors.white,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewRecipeLinkDialog() {
    RecipeLinkDialog()
}