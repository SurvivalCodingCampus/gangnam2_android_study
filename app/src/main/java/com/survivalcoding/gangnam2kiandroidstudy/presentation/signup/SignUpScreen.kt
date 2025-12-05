package com.survivalcoding.gangnam2kiandroidstudy.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SignUpScreen(
    onSignUpClick: () -> Unit = {},
    ) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 30.dp),
        ) {

        Spacer(modifier = Modifier.height(54.dp))

        Column() {
            Text(
                "Create an account",
                style = AppTextStyles.largeTextBold,
                color = AppColors.black,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Let's help you set up your account, it won't take long.",
                style = AppTextStyles.smallerTextRegular,
                color = AppColors.label1,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        InputField(
            label = "Name",
            placeholder = "Enter name",
            value = name,
            onValueChange = { name = it },
        )

        Spacer(modifier = Modifier.height(10.dp))

        InputField(
            label = "Email",
            placeholder = "Enter email",
            value = email,
            onValueChange = { email = it },
        )

        Spacer(modifier = Modifier.height(10.dp))

        InputField(
            label = "Password",
            placeholder = "Enter password",
            value = password,
            onValueChange = { password = it },
        )

        Spacer(modifier = Modifier.height(20.dp))

        InputField(
            label = "Confirm Password",
            placeholder = "Confirm password",
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box() {

        }

    }
}


@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}
