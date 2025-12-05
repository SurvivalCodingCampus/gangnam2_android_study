package com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SignInScreen(
    onSignInClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onGoogleLoginClick: () -> Unit = {},
    onFacebookLoginClick: () -> Unit = {},
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.weight(94f))
            Column {
                Text(
                    text = "Hello,",
                    fontWeight = FontWeight.SemiBold,
                    style = AppTextStyles.headerTextRegular
                )
                Text(
                    text = "Welcome Back!",
                    fontWeight = FontWeight.Medium,
                    style = AppTextStyles.largeTextRegular
                )
            }
            Spacer(modifier = Modifier.height(57.dp))
            InputField(
                label = "Email",
                value = email,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { email = it },
                placeholder = "Enter Email"
            )
            Spacer(modifier = Modifier.height(30.dp))
            InputField(
                label = "Enter Password",
                value = password,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { password = it },
                placeholder = "Enter Password"
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Forgot Password?",
                color = AppColors.secondary100,
                modifier = Modifier
                    .padding(top = 8.dp, start = 10.dp)
                    .clickable { onForgotPasswordClick() },
                style = AppTextStyles.smallerTextRegular
            )
            Spacer(modifier = Modifier.height(25.dp))
            BigButton("Sign In", onClick = onSignInClick)
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f).padding(end = 7.dp), color = Color.LightGray)
                Text(
                    text = "Or Sign in With",
                    color = AppColors.gray4,
                    style = AppTextStyles.smallerTextRegular
                )
                HorizontalDivider(modifier = Modifier.weight(1f).padding(start = 7.dp), color = Color.LightGray)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                SocialButton(
                    iconRes = R.drawable.ic_google,
                    modifier = Modifier.size(44.dp),
                    onClick = onGoogleLoginClick
                )
                Spacer(modifier = Modifier.width(25.dp))
                SocialButton(
                    iconRes = R.drawable.ic_facebook,
                    modifier = Modifier.size(44.dp),
                    onClick = onFacebookLoginClick
                )
            }
            Spacer(modifier = Modifier.height(55.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account? ",
                    fontSize = 14.sp, color = Color.Black,
                    style = AppTextStyles.smallerTextRegular
                )
                Text(
                    text = "Sign up",
                    fontSize = 14.sp,
                    color = Color(0xFFFF9800),
                    modifier = Modifier.clickable { onSignUpClick() },
                    style = AppTextStyles.smallerTextRegular
                )
            }
            Spacer(modifier = Modifier.weight(99f))
        }
    }
}

// 소셜 로그인 버튼
@Composable
fun SocialButton(
    iconRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick,
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(
            horizontal = 0.dp,
            vertical = 0.dp
        ),
        colors = ButtonColors(
            containerColor = AppColors.white,
            contentColor = AppColors.white,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.White
        ),
        elevation = ButtonDefaults.elevatedButtonElevation()
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}