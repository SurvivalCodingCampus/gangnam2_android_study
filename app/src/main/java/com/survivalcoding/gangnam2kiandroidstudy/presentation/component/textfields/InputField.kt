package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.textfields

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun InputField(
    value: String,
    label: String = "Label",
    onValueChange: (String) -> Unit,
    placeholderText: String = "Placeholder",
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current


    Column(
        modifier = modifier
    ) {
        Text(
            text = label,
            style = AppTextStyles.smallTextRegular
        )
        Spacer(Modifier.size(5.dp))
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            enabled = enabled,
            placeholder = {
                Text(
                    text = placeholderText,
                    fontSize = 11.sp
                )
            },
            shape = RoundedCornerShape(10.dp),
            modifier = modifier
                .size(width = 315.dp, height = 55.dp)
                .onFocusChanged() { focusState ->
                    if (!focusState.isFocused) {
                        focusManager.clearFocus()
                    }
                },
            interactionSource = interactionSource,
            singleLine = true,
            textStyle = AppTextStyles.smallerTextRegular.copy(fontSize = 11.sp),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = AppColors.black,

                focusedContainerColor = AppColors.white,
                focusedTextColor = AppColors.black,
                focusedBorderColor = AppColors.primary80,

                unfocusedContainerColor = AppColors.white,
                unfocusedTextColor = AppColors.black,
                unfocusedBorderColor = AppColors.gray4,
                unfocusedPlaceholderColor = AppColors.gray4,

                disabledContainerColor = AppColors.gray4,
                disabledTextColor = AppColors.gray2,
                disabledBorderColor = AppColors.gray4
            ),
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                },
                onNext = {
                    focusManager.clearFocus()
                }
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InputFieldPreview() {
    var input by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(20.dp)
    ) {

        // Default
        InputField(
            value = input,
            onValueChange = {},
            placeholderText = "Placeholder"
        )

        // Disabled
        InputField(
            value = "",
            onValueChange = {},
            placeholderText = "Placeholder",
            enabled = false
        )
    }
}