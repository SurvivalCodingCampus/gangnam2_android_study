package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppTextStyles

@Composable
fun SignInScreen(onSignUpButtonClick: () -> Unit, onSignInButtonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(94.dp))
        Text("Hello,", style = AppTextStyles.headerTextBold)
        Text("welcome Back!", style = AppTextStyles.largeTextRegular)
        Spacer(modifier = Modifier.height(57.dp))
        InputField(label = "Email", placeholder = "Enter Email", value = "", onValueChange = {})
        Spacer(modifier = Modifier.height(30.dp))
        InputField(
            label = "Enter Password",
            placeholder = "Enter Password",
            value = "",
            onValueChange = {})
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "Forgot Password?",
            modifier = Modifier.padding(start = 10.dp),
            style = AppTextStyles.smallerTextRegular.copy(color = AppColors.secondary100)
        )
        Spacer(modifier = Modifier.height(25.dp))
        BigButton(text = "Sign In", onClick = { onSignInButtonClick() })
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.height(17.dp), verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(60.dp))
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .width(1.dp)
            )
            Spacer(modifier = Modifier.width(7.dp))
            Text(
                "Or Sign in With",
                modifier = Modifier,
                style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray4)
            )
            Spacer(modifier = Modifier.width(7.dp))
            HorizontalDivider(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(60.dp))
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier, horizontalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .background(
                        color = AppColors.white,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .size(44.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(painterResource(R.drawable.social_icons_google), contentDescription = "구글로고")
            }
            Spacer(modifier = Modifier.width(25.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = AppColors.white,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .size(44.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painterResource(R.drawable.social_icons_facebook),
                    contentDescription = "페북로고"
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(55.dp))
        Row(modifier = Modifier) {
            Spacer(modifier = Modifier.weight(1f))
            Text("Don't have an account?", style = AppTextStyles.smallerTextRegular)
            Text(
                " Sign Up",
                style = AppTextStyles.smallerTextRegular.copy(color = AppColors.secondary100),
                modifier = Modifier.clickable {
                    onSignUpButtonClick()
                }
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(99.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview(modifier: Modifier = Modifier) {
    SignInScreen({}, {})
}