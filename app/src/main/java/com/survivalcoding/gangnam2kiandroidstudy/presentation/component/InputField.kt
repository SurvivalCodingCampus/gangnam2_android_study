package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun InputField(label: String, placeholder: String, value: String, onValueChange: (String) -> Unit) {
    Box(
        modifier = Modifier.size(width = 315.dp, height = 81.dp)
    ) {
        Column(modifier = Modifier) {
            Text(
                modifier = Modifier.size(width = 38.dp, height = 21.dp),
                text = label,
                style = AppTextStyles.smallTextRegular
            )
            Spacer(modifier = Modifier.height(5.dp))
            Box( // TextField를 감싸는 Box 추가
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(Color.White, RoundedCornerShape(12.dp)) // Box에 배경색과 둥근 모서리 적용

            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxSize(), // TextField가 Box를 가득 채우도록 설정
                    textStyle = AppTextStyles.smallerTextRegular.copy(color = AppColors.black),
                    onValueChange = onValueChange,
                    value = value,
                    placeholder = { // placeholder 람다 추가
                        Text(
                            text = placeholder,
                            style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray4)
                        ) // placeholder 텍스트 설정
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = AppColors.white,
                        unfocusedContainerColor = AppColors.white,
                        focusedBorderColor = AppColors.primary80,
                        unfocusedBorderColor = AppColors.gray4,
                    )
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun InputFieldPreview1() { //Default
    InputField("Label", "Placeholder", "", onValueChange = {})
}

@Preview(showBackground = true)
@Composable
fun InputFieldPreview2() { //filled
    InputField("Label", "", "filled", onValueChange = {})
}