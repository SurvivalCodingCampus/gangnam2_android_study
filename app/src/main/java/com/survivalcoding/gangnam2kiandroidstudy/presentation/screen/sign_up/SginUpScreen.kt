package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.auth.AppCheckBox
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.auth.AuthBottomText
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.auth.CenterTextDivider
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.auth.SocialLoginRow
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.textfields.InputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    state: SignUpState,
    onAction: (SignUpAction) -> Unit,
) {
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val confirmPasswordFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {

        item {
            Column(
                modifier = Modifier.padding(top = 10.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = stringResource(R.string.signup_title),
                    style = AppTextStyles.largeTextBold,
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = stringResource(R.string.signup_subtitle),
                    style = AppTextStyles.smallerTextRegular,
                    lineHeight = 17.sp,
                    modifier = Modifier.size(195.dp, 34.dp)
                )
            }
        }

        item { Spacer(Modifier.height(20.dp)) }

        item {
            InputField(
                value = state.name,
                label = stringResource(R.string.label_name),
                placeholderText = stringResource(R.string.placeholder_name),
                onValueChange = {
                    onAction(SignUpAction.NameChanged(it))
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions {
                    emailFocusRequester.requestFocus()
                }
            )
        }

        item { Spacer(Modifier.height(20.dp)) }

        item {
            InputField(
                value = state.email,
                label = stringResource(R.string.label_email),
                placeholderText = stringResource(R.string.placeholder_email),
                onValueChange = {
                    onAction(SignUpAction.EmailChanged(it))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions {
                    passwordFocusRequester.requestFocus()
                },
                modifier = Modifier.focusRequester(emailFocusRequester)
            )
        }

        item { Spacer(Modifier.height(20.dp)) }

        item {
            InputField(
                value = state.password,
                label = stringResource(R.string.label_password),
                placeholderText = stringResource(R.string.placeholder_password),
                onValueChange = {
                    onAction(SignUpAction.PasswordChanged(it))
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions {
                    confirmPasswordFocusRequester.requestFocus()
                },
                modifier = Modifier.focusRequester(passwordFocusRequester)
            )
        }

        item { Spacer(Modifier.height(20.dp)) }

        item {
            InputField(
                value = state.confirmPassword,
                label = stringResource(R.string.label_confirm_password),
                placeholderText = stringResource(R.string.placeholder_confirm_password),
                onValueChange = {
                    onAction(SignUpAction.ConfirmPasswordChanged(it))
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions {
                    focusManager.clearFocus()
                },
                modifier = Modifier.focusRequester(confirmPasswordFocusRequester)
            )
        }

        item {
            Row(
                modifier = Modifier.padding(top = 20.dp, bottom = 26.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppCheckBox(
                    checked = state.isTermsChecked,
                    onCheckedChange = {
                        onAction(SignUpAction.TermsChecked(it))
                    }
                )

                Spacer(Modifier.width(5.dp))

                Text(
                    text = stringResource(R.string.accept_terms),
                    style = AppTextStyles.smallerTextRegular.copy(
                        color = AppColors.secondary100
                    )
                )
            }
        }

        item {
            BigButton(
                text = stringResource(R.string.button_sign_up),
                enabled = state.isSignUpEnabled,
                onClick = {
                    onAction(SignUpAction.ClickSignUp)
                }
            )
        }

        item {
            CenterTextDivider(stringResource(R.string.divider_sign_in_with))
        }

        item {
            SocialLoginRow(
                onGoogleClick = {
                    onAction(SignUpAction.ClickGoogleSignUp)
                }
            )
        }

        item { Spacer(Modifier.height(20.dp)) }

        item {
            AuthBottomText(
                promptText = stringResource(R.string.prompt_already_member),
                actionText = stringResource(R.string.action_sign_in_again),
                onActionClick = {
                    onAction(SignUpAction.ClickNavigateToSignIn)
                }
            )
        }
    }
}
