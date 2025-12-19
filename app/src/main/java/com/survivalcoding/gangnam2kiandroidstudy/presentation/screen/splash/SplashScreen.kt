package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.MediumButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes.SearchRecipesAction
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onAction: (SplashAction) -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize()) {

        // 배경
        Image(
            painter = painterResource(R.drawable.bg_splash),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_chef),
                contentDescription = null,
                tint = AppColors.white,
                modifier = Modifier.size(79.dp)
            )

            Text(
                text = stringResource(R.string.splash_description),
                style = AppTextStyles.mediumTextBold.copy(color = AppColors.white),
                modifier = Modifier.padding(top = 14.dp)
            )
        }


        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp, start = 40.dp, end = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 중앙 제목 영역
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.splash_title),
                    style = AppTextStyles.titleTextBold.copy(
                        color = AppColors.white,
                        fontSize = 50.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 60.sp
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.splash_subtitle),
                    style = AppTextStyles.normalTextRegular.copy(color = AppColors.white),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(64.dp)) // ⬅️ 정확한 버튼 위 간격

            MediumButton(
                text = stringResource(R.string.splash_button),
                onClick = {
                    onAction(SplashAction.ClickStratButton)
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    SplashScreen(
        onAction = { /* preview no-op */ }
    )
}