package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun InputField(
    label: String,
    placeholder: String,
    value: String?,
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier.size(width = 315.dp, height = 81.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = label,
            style = AppTextStyles.smallTextRegular
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    style = AppTextStyles.smallTextRegular.copy(color = AppColors.gray4)
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = AppColors.white,
                focusedIndicatorColor = AppColors.primary80,
                focusedContainerColor = AppColors.white,
                unfocusedIndicatorColor = AppColors.gray4,
            ),

            value = value ?: "",
            onValueChange = onValueChange,
            shape = RoundedCornerShape(10.dp),

            )

    }

}


@Preview(showBackground = true)
@Composable
fun InputFieldPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputField(
            label = "Label",
            placeholder = "Placeholder",
            value = "Placeholder",
        ) {
            println(it)
        }
        InputField(
            label = "Label",
            placeholder = "Placeholder",
            value = null,
        ) {
            println(it)
        }
    }
}