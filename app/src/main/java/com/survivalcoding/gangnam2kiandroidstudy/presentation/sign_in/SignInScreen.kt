package com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_in

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.textfields.InputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
) {
    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }

    Column(
        modifier = modifier,
    ) {
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

        InputField(
            value = emailInput,
            label = stringResource(R.string.label_email),
            onValueChange = {},
            placeholderText = stringResource(R.string.placeholder_email),
        )

        Spacer(Modifier.height(30.dp))

        InputField(
            value = passwordInput,
            label = stringResource(R.string.label_password),
            onValueChange = {},
            placeholderText = stringResource(R.string.placeholder_password),
        )

        Text(
            modifier = Modifier.padding(top = 20.dp, bottom = 25.dp),
            text = stringResource(R.string.forgot_password),
            style = AppTextStyles.smallerTextRegular.copy(color = AppColors.secondary100)
        )

        BigButton(
            text = stringResource(R.string.button_sign_in),
        )

        Row(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 60.dp)
                .height(17.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = 1.dp,
                color = AppColors.gray4
            )

            Text(
                modifier = Modifier.padding(horizontal = 7.dp),
                text = stringResource(R.string.divider_sign_in_with),
                style = AppTextStyles.smallerTextRegular.copy(
                    color = AppColors.gray4
                )
            )

            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = 1.dp,
                color = AppColors.gray4
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 91.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

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
                    painter = painterResource(R.drawable.ic_google),
                    contentDescription = "google sign in",
                    tint = Color.Unspecified
                )
            }

            Spacer(Modifier.width(25.dp))

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
                    painter = painterResource(R.drawable.ic_facebook),
                    contentDescription = "facebook sign in",
                    tint = Color.Unspecified
                )
            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 55.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.prompt_no_account),
                style = AppTextStyles.smallerTextBold
            )
            Spacer(Modifier.width(2.dp))
            Text(
                text = stringResource(R.string.action_sign_up),
                style = AppTextStyles.smallerTextBold.copy(
                    color = AppColors.secondary100
                )
            )
        }


    }
}


@Preview(showBackground = true)
@Composable
fun SignInScreenPreview(modifier: Modifier = Modifier.padding(horizontal = 30.dp)) {
    SignInScreen(modifier = modifier.fillMaxSize())
}