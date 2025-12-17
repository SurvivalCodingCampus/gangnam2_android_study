package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppColors

@Composable
fun CustomSearchField(onValueChange: (String) -> Unit) {
    var inputText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .border(width = 1.3.dp, color = AppColors.gray4, shape = RoundedCornerShape(10.dp))
    ) {
        Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(18.dp),
                painter = painterResource(R.drawable.outline_search_normal),
                contentDescription = "검색 아이콘",
                tint = AppColors.gray4
            )
            BasicTextField(
                value = inputText,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    onValueChange(it)
                    inputText = it
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomSearchFieldPreview() {
    CustomSearchField(onValueChange = {

    })
}