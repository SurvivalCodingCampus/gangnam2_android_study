package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun Search(onValueChange: (String) -> Unit) {
    var inputText by remember { mutableStateOf("") }
    OutlinedTextField(
        //TODO 글자 짤리는 수정해야하는데 아마 새로 만들어서 써야할 듯
        modifier = Modifier.height(40.dp),


        value = inputText,
        onValueChange = {
            inputText = it
            onValueChange(inputText)
        },
        shape = RoundedCornerShape(10.dp),


        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.outline_search_normal),
                contentDescription = "검색 아이콘",
                tint = AppColors.gray4
            )
        },
        textStyle = AppTextStyles.smallerTextRegular.copy(fontSize = 11.sp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AppColors.primary80,
            unfocusedBorderColor = AppColors.gray4,
        ),
    )


}

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    Search() {

    }
}