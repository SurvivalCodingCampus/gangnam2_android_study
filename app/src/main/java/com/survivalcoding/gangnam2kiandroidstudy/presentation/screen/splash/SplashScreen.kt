package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.MediumButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SplashScreen(onStartButtonClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.splash_image),
            contentDescription = "스플래시 이미지",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,

            )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = AppColors.splashScreenGradient)
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(104.dp))
            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.splash_top_img),
                contentDescription = "스플레시 화면 상단 사진",
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                "100K+ Premium Recipe",
                modifier = Modifier,
                style = AppTextStyles.mediumTextBold.copy(
                    color = AppColors.white,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.height(222.dp))
            Text(
                "Get\nCooking",
                modifier = Modifier,
                style = AppTextStyles.titleTextBold.copy(
                    color = AppColors.white,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = (50.sp * 1.2f) //figma상 120%로 나와있어서
                ),
                textAlign = TextAlign.Center
            )
            Text(
                "Simple way to find Tasty Recipe",
                modifier = Modifier,
                style = AppTextStyles.normalTextRegular.copy(color = AppColors.white)
            )
            Spacer(modifier = Modifier.height(64.dp))
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                MediumButton("Start Cooking") {
                    onStartButtonClick()

                }
            }
            Spacer(modifier = Modifier.height(84.dp))


        }


    }

}

@Preview(showBackground = true, heightDp = 812)
@Composable
fun SplashScreenPreview() {
    SplashScreen({})
}