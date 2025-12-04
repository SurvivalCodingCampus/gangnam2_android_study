package com.survivalcoding.gangnam2kiandroidstudy.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton

@Composable
fun CounterScreen(modifier: Modifier = Modifier) {
    val count = rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("${count.value}", fontSize = 35.sp)

        BigButton(
            text = "+",
            onClick = {
                count.value++
            },
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun CounterScreenPreview() {
    CounterScreen()
}