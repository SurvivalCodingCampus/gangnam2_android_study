package com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_up

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
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
fun SignUpScreen() {
    var isChecked by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var checkPassword by remember { mutableStateOf("") }


    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(start = 30.dp, top = 54.dp, bottom = 20.dp, end = 150.dp)
        ) {
            Text(
                text = "Create an account",
                style = AppTextStyles.largeTextBold,
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Let’s help you set up your account, it won’t take long.",
                style = AppTextStyles.smallerTextRegular
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            InputField(
                label = "Name",
                placeholder = "Enter Name",
                value = name,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
            ) {
                name = it
            }

            Spacer(modifier = Modifier.height(20.dp))

            InputField(
                label = "Email",
                placeholder = "Enter Email",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                value = email
            ) {
                email = it
            }

            Spacer(modifier = Modifier.height(20.dp))

            InputField(
                label = "Password",
                placeholder = "Enter Password",
                value = password,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                visualTransformation = PasswordVisualTransformation(),
            ) {
                password = it
            }

            Spacer(modifier = Modifier.height(20.dp))

            InputField(
                label = "Confirm Password",
                placeholder = "Retype Password",
                value = checkPassword,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                visualTransformation = PasswordVisualTransformation()
            ) {
                checkPassword = it
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .size(17.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color.White)
                        .border(
                            width = 1.dp,
                            color = AppColors.secondary100,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .clickable {
                            isChecked = !isChecked
                        }
                ) {
                    if (isChecked) {
                        Icon(Icons.Outlined.Check, "check", tint = AppColors.secondary100)
                    }
                }
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Accept terms & Condition",
                    style = AppTextStyles.smallTextRegular.copy(color = AppColors.secondary100),
                )
            }


            Spacer(modifier = Modifier.height(25.dp))
            BigButton(
                text = "Sign Up",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(14.dp))
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
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = buildAnnotatedString {
                    append("Already a member? ")
                    withStyle(
                        style = SpanStyle(
                            color = AppColors.secondary100,
                        )
                    ) {
                        append("Sign In")
                    }
                },
                style = AppTextStyles.smallerTextBold,
                modifier = Modifier.padding(bottom = 30.dp)
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}