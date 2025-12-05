package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun RatingDialog(
    onSubmit: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var rating by rememberSaveable { mutableStateOf(0) }

    Column {
        Row() {
            Button(onClick = {
                rating--
            }) {
                Text(text = "-")
            }

            Text(text = "$rating", fontSize = 30.sp)

            Button(onClick = {
                rating++
            }) {
                Text(text = "+")
            }
        }
        Button(
            onClick = {
                onSubmit(rating)
            },
            enabled = rating > 0,
        ) {
            Text(text = "확인")
        }
    }


}

@Preview(showBackground = true)
@Composable
private fun RatingDialogPreview() {
    var rating by remember { mutableStateOf(0) }

    Column {
        Text("$rating")
        RatingDialog(
            onSubmit = {
                rating = it
            },
        )
    }

}