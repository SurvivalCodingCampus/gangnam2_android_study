import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun RecipeLinkDialog(
    recipeLink: String,
    onDismiss: () -> Unit,
    onCopyClick: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = AppColors.white,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(310.dp)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 15.dp)
            ) {

                // 닫기 아이콘
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "Close",
                        modifier = Modifier
                            .size(5.dp)
                            .clickable(onClick = onDismiss)
                    )
                }

                // Title
                Text(
                    text = "Recipe Link",
                    style = AppTextStyles.largeTextBold
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Description
                Text(
                    text = "Copy recipe link and share your recipe link with friends and family.",
                    style = AppTextStyles.smallerTextRegular.copy(
                        color = AppColors.gray2,
                        lineHeight = 15.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                // 링크 + 버튼
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(43.dp)
                ) {
                    // 회색 링크 박스
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = AppColors.gray4,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 12.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = recipeLink,
                            style = AppTextStyles.smallerTextRegular
                        )
                    }

                    // 버튼 (위에 겹침)
                    SmallButton(
                        text = "Copy Link",
                        modifier = Modifier
                            .size(width = 85.dp, height = 43.dp)
                            .align(Alignment.CenterEnd),
                        onClick = onCopyClick
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeLinkDialogPreview() {
    RecipeLinkDialog(
        recipeLink = "app.recipe.co/jollof_rice",
        onDismiss = {},
        onCopyClick = {}
    )
}

