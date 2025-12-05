package com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_in

import android.R.attr.label
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SocialButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SignInScreen() {
    val focusManager = LocalFocusManager.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(start = 30.dp, top = 94.dp, bottom = 57.dp)
        ) {
            Text(
                text = "Hello,",
                style = AppTextStyles.headerTextBold,
            )
            Text(
                text = "Welcome Back!",
                style = AppTextStyles.largeTextRegular
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            InputField(
                label = "Email",
                placeholder = "Enter Email",
                value = email,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
            ) {
                email = it
            }
            Spacer(modifier = Modifier.height(30.dp))

            InputField(
                label = "Enter Password",
                placeholder = "Enter Password",
                value = password,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                visualTransformation = PasswordVisualTransformation()
            ) {
                password = it
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Forgot Password?",
                style = AppTextStyles.smallTextRegular.copy(color = AppColors.secondary100),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 10.dp)
            )
            Spacer(modifier = Modifier.height(25.dp))
            BigButton(
                text = "Sign In",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.width(50.dp), color = AppColors.gray4)
                Text(
                    text = "Or Sign in With",
                    modifier = Modifier.padding(horizontal = 7.dp),
                    style = AppTextStyles.smallerTextBold.copy(color = AppColors.gray4)
                )
                HorizontalDivider(modifier = Modifier.width(50.dp), color = AppColors.gray4)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                SocialButton(
                    id = R.drawable.social_icons_google,
                    description = "구글 로그인"
                )
                Spacer(modifier = Modifier.width(25.dp))
                SocialButton(
                    id = R.drawable.social_icons_facebook,
                    description = "페이스북 로그인"
                )
            }
            Spacer(modifier = Modifier.height(55.dp))

            Text(
                text = buildAnnotatedString {
                    append("Don't have an account? ")
                    withStyle(
                        style = SpanStyle(
                            color = AppColors.secondary100,
                        )
                    ) {
                        append("Sign up")
                    }
                },
                style = AppTextStyles.smallerTextBold
            )


        }

    }
}


@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    SignInScreen()
}