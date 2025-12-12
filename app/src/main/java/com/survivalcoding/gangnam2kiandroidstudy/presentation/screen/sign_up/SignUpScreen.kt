package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SignUpScreen(onSignInButtonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 30.dp), verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(height = 54.dp))
        Text("Create an account", modifier = Modifier, style = AppTextStyles.largeTextBold)
        Text(
            "Let’s help you set up your account,\nit won’t take long.",
            style = AppTextStyles.smallerTextRegular
        )
        Spacer(modifier = Modifier.height(height = 20.dp))
        InputField(label = "Name", placeholder = "Enter Name", value = "", onValueChange = {})
        Spacer(modifier = Modifier.height(height = 20.dp))
        InputField(label = "Email", placeholder = "Enter Email", value = "", onValueChange = {})
        Spacer(modifier = Modifier.height(height = 20.dp))
        InputField(
            label = "Password",
            placeholder = "Enter Password",
            value = "",
            onValueChange = {})
        Spacer(modifier = Modifier.height(height = 20.dp))
        InputField(
            label = "Confirm Password",
            placeholder = "Retype Password",
            value = "",
            onValueChange = {})
        Spacer(modifier = Modifier.height(height = 20.dp))
        Row(modifier = Modifier.padding(start = 10.dp)) {
            Box(
                modifier = Modifier
                    .size(17.dp)
                    .border(
                        width = 1.dp,
                        color = AppColors.secondary100,
                        shape = RoundedCornerShape(5.dp)
                    )
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Accept terms & Condition",
                style = AppTextStyles.smallerTextRegular.copy(color = AppColors.secondary100)
            )
        }
        Spacer(modifier = Modifier.height(height = 26.dp))
        BigButton(text = "Sign Up", onClick = {})
        Spacer(modifier = Modifier.height(14.dp))
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
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier) {
            Spacer(modifier = Modifier.weight(1f))
            Text("Already a member?", style = AppTextStyles.smallerTextRegular)
            Text(
                " Sign Up",
                style = AppTextStyles.smallerTextRegular.copy(color = AppColors.secondary100),
                modifier = Modifier.clickable {
                    onSignInButtonClick()
                }
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(30.dp))


    }

}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview(modifier: Modifier = Modifier) {
    SignUpScreen() {

    }

}