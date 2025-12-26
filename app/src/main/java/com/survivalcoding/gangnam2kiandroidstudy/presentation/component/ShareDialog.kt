package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun ShareDialog(
    modifier: Modifier = Modifier,
    recipeLink: String = "app.Recipe.co/jollof _rice",
    onDismiss: () -> Unit = {},
    onCopy: (String) -> Unit = {}
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Card(
            modifier = modifier
                .width(310.dp)
                .aspectRatio(310 / 167f),
            colors = CardDefaults.cardColors(
                containerColor = AppColors.white
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Spacer(Modifier.height(15.dp))

                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "close",
                    tint = AppColors.gray1,
                    modifier = modifier
                        .size(5.dp)
                        .align(Alignment.End)
                        .clickable {
                            onDismiss()
                        }
                )
                Text(
                    text = "Recipe Link",
                    style = AppTextStyles.largeTextBold
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = "Copy recipe link and share your recipe link with  friends and family.",
                    style = AppTextStyles.smallerTextRegular.copy(
                        color = AppColors.gray2,
                        lineHeight = 18.sp
                    )
                )

                Spacer(Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(43.dp)
                        .background(color = AppColors.gray4, shape = RoundedCornerShape(9.dp)),
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Spacer(modifier = Modifier.width(14.dp))
                        Text(
                            text = recipeLink,
                            style = AppTextStyles.smallerTextBold
                        )
                    }
                    SmallButton(
                        text = "Copy Link",
                        modifier = Modifier
                            .size(85.dp, 43.dp)
                            .align(Alignment.TopEnd)
                    ) {
                        onCopy(recipeLink)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ShareDialogPreview() {
    ShareDialog()
}