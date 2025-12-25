package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun ShareRecipeDialog(
    recipeLink: String,
    onDismiss: () -> Unit,
    onCopyClick: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(20.dp))
                .padding(15.dp)
        ) {
            Column {
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = null,
                        modifier = Modifier
                            .size(5.dp)
                            .clickable { onDismiss() }
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Recipe Link",
                        style = AppTextStyles.largeTextRegular.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Copy recipe link and share your recipe link with friends and family.",
                    style = AppTextStyles.smallerTextRegular.copy(
                        fontWeight = FontWeight.Medium,
                        color = AppColors.gray2
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = recipeLink,
                        modifier = Modifier.padding(start = 14.dp).weight(1f),
                        style = AppTextStyles.smallerTextRegular,
                        maxLines = 1
                    )
                    Button(
                        onClick = onCopyClick,
                        modifier = Modifier.size(width = 85.dp, height = 43.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColors.primary100
                        ),
                        contentPadding = PaddingValues()
                    ) {
                        Text(
                            text = "Copy Link",
                            style = AppTextStyles.smallerTextRegular.copy(
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShareRecipeDialogPreview() {
    ShareRecipeDialog("app.Recipe.co/jollof_rice", fun() = Unit, fun() = Unit)
}