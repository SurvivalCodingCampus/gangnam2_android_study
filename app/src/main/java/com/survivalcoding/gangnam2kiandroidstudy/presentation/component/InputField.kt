package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun InputField(
    label: String, value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    focusedBorderColor: Color = AppColors.primary80,
    unfocusedBorderColor: Color = Color(0xFFD9D9D9)
) {
    Column {
        Text(
            text = label,
            style = AppTextStyles.smallTextRegular,
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.padding(top = 5.dp).size(width = 315.dp, height = 55.dp),
            textStyle = AppTextStyles.smallerTextRegular,
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color(0xFFD9D9D9),
                    style = AppTextStyles.smallerTextRegular
                )
            },
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = focusedBorderColor,   // 초록 테두리
                unfocusedBorderColor = unfocusedBorderColor, // 흰색 테두리
                disabledBorderColor = Color(0xFFD9D9D9),
                disabledTextColor = Color(0xFFD9D9D9),
                disabledPlaceholderColor = Color(0xFF797979),
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
    InputField("Label", "", {}, "Placeholder")
}