package com.survivalcoding.gangnam2kiandroidstudy.presentation.counter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CounterScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: CounterViewModel = viewModel(),
) {
    val countValue by viewModel.count.collectAsState()

    CounterScreen(
        countValue = countValue,
        onIncrement = viewModel::increment
    )
}

@Composable
fun CounterScreen(
    modifier: Modifier = Modifier,
    countValue: Int,
    onIncrement: () -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("$countValue")
        Button(onClick = {
            onIncrement()
        }) {
            Text("+")
        }
    }
}

