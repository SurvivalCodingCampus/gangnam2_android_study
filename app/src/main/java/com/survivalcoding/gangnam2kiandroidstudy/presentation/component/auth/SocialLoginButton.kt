package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors


@Composable
fun SocialLoginRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SocialLoginButton(
            iconRes = R.drawable.ic_google,
            contentDescription = stringResource(R.string.icon_google_description)
        )
        SocialLoginButton(
            iconRes = R.drawable.ic_facebook,
            contentDescription = stringResource(R.string.icon_facebook_description)
        )
    }
}


@Composable
fun SocialLoginButton(iconRes: Int, contentDescription: String) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(10.dp),
                clip = false,
                ambientColor = Color(0x1A696969),
            )
            .background(
                color = AppColors.white,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = contentDescription,
            tint = Color.Unspecified
        )
    }
}
