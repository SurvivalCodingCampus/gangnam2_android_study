package com.survivalcoding.gangnam2kiandroidstudy.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.MediumButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SplashScreen(
    state: SplashState = SplashState(),
    onAction: (SplashAction) -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.splash_background),
            contentDescription = "Splash Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(104.dp))
            Image(
                painter = painterResource(R.drawable.splash_logo),
                contentDescription = "Splash Logo",
                modifier = Modifier.size(79.dp)
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "100K+ Premium Recipe",
                style = AppTextStyles.mediumTextBold.copy(color = AppColors.white)
            )
            Spacer(modifier = Modifier.height(222.dp))
            Text(
                text = "Get Cooking",
                style = AppTextStyles.titleTextBold.copy(color = AppColors.white),
                modifier = Modifier.padding(horizontal = 81.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Simple way to find Tasty Recipe",
                style = AppTextStyles.normalTextRegular.copy(color = AppColors.white),
                modifier = Modifier.padding(horizontal = 50.dp)
            )
            Spacer(modifier = Modifier.height(64.dp))
            MediumButton(
                text = "Start Cooking",
                enabled = state.isConnected,
                modifier = Modifier
                    .padding(horizontal = 66.dp)
                    .padding(bottom = 84.dp)
            ) {
                onAction(SplashAction.OnStartClick)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    SplashScreen()

}