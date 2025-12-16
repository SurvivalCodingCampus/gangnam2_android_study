package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_in

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.auth.AuthBottomText
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.auth.CenterTextDivider
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.auth.SocialLoginRow
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.textfields.InputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    state: SignInState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    val passwordFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {

        item {
            Column(
                modifier = Modifier.padding(top = 50.dp, bottom = 57.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = stringResource(R.string.welcome_title),
                    style = AppTextStyles.headerTextBold,
                )
                Text(
                    text = stringResource(R.string.welcome_subtitle),
                    style = AppTextStyles.largeTextRegular,
                )
            }
        }

        item {
            InputField(
                value = state.email,
                label = stringResource(R.string.label_email),
                onValueChange = onEmailChange,
                placeholderText = stringResource(R.string.placeholder_email),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        passwordFocusRequester.requestFocus()
                    }
                )
            )
        }

        item { Spacer(Modifier.height(30.dp)) }

        item {
            InputField(
                value = state.password,
                label = stringResource(R.string.label_password),
                onValueChange = onPasswordChange,
                placeholderText = stringResource(R.string.placeholder_password),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                modifier = Modifier.focusRequester(passwordFocusRequester)
            )
        }

        item {
            Text(
                modifier = Modifier.padding(top = 20.dp, bottom = 25.dp),
                text = stringResource(R.string.forgot_password),
                style = AppTextStyles.smallerTextRegular.copy(color = AppColors.secondary100)
            )
        }

        item {
            BigButton(
                text = stringResource(R.string.button_sign_in),
                enabled = state.isSignInEnabled,
                onClick = onSignInClick,
            )
        }

        item {
            CenterTextDivider(stringResource(R.string.divider_sign_in_with))
        }

        item {
            SocialLoginRow()
        }

        item { Spacer(Modifier.height(55.dp)) }

        item {
            AuthBottomText(
                promptText = stringResource(R.string.prompt_no_account),
                actionText = stringResource(R.string.action_sign_up),
                onActionClick = onSignUpClick
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        state = SignInState(
            email = "test@email.com",
            password = "1234",
            isSignInEnabled = true
        ),
        onEmailChange = {},
        onPasswordChange = {},
        onSignInClick = {},
        onSignUpClick = {}
    )
}
