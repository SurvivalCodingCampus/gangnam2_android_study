package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

data class FilterSearchState(
    val count1: Int = 0,
    val count2: Int = 0,
)

@Composable
fun FilterSearchBottomSheet(
    modifier: Modifier = Modifier,
    state: FilterSearchState,
    onClickFilter: (FilterSearchState) -> Unit = {},
) {
    var count1 by remember { mutableStateOf(state.count1) }
    var count2 by remember { mutableStateOf(state.count2) }

    Column() {
        Row {
            ElevatedButton(onClick = {
                count1--
            }) {
                Text("-")
            }
            Text("$count1")
            ElevatedButton(onClick = {
                count1++
                state.copy(count1 = count1)
            }) {
                Text("+")
            }
        }
        Row {
            ElevatedButton(onClick = {
                count2--
            }) {
                Text("-")
            }
            Text("$count2")
            ElevatedButton(onClick = {
                count2++
            }) {
                Text("+")
            }
        }
        ElevatedButton(onClick = {
            onClickFilter(
                FilterSearchState(
                    count1 = count1,
                    count2 = count2,
                )
            )
        }) {
            Text("Filter")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FilterSearchBottomSheetPreview() {
    FilterSearchBottomSheet(
        state = FilterSearchState(
            count1 = 2,
            count2 = 3,
        ),
        onClickFilter = {},
    )
}