package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun InputField(
    label: String, value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isInputTypePassword: Boolean = false,
    focusedBorderColor: Color = AppColors.primary80,
    unfocusedBorderColor: Color = AppColors.gray4
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = AppTextStyles.smallTextRegular,
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.padding(top = 5.dp).fillMaxWidth().height(55.dp),
            textStyle = AppTextStyles.smallerTextRegular,
            placeholder = {
                Text(
                    text = placeholder,
                    color = AppColors.gray4,
                    style = AppTextStyles.smallerTextRegular
                )
            },
            visualTransformation =
                if (isInputTypePassword) PasswordVisualTransformation() else VisualTransformation.None,
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = focusedBorderColor,
                unfocusedBorderColor = unfocusedBorderColor,
                disabledBorderColor = AppColors.gray4,
                disabledTextColor = AppColors.gray4,
                disabledPlaceholderColor = AppColors.gray2,
                focusedLabelColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
                cursorColor = focusedBorderColor
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun InputFieldPreview() {
    InputField(label = "Label", value = "", onValueChange = {}, placeholder = "Placeholder")
}