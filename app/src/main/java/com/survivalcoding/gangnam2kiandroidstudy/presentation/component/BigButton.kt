package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun BigButton(onClick: () -> Unit, text:String){
    Box(modifier = Modifier
        .size(width = 315.dp, height = 60.dp)
        .background(color = AppColors.primary100)
        , ){

        Row {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center

            ) {
                TextField(
                    value = text, onValueChange = {},
                    modifier = Modifier
                        .size(width = 114.dp, height = 24.dp),
                )

            }
        }

    }

}

@Preview
@Composable
fun BigButtonPreview(){
    BigButton(onClick ={}, text = "버튼1")
}