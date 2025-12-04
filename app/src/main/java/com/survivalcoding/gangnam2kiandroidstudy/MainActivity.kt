package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Components()
        }
    }
}

@Composable
fun Components() {
    var text by remember { mutableStateOf("") }
    var selectedFirstTab by remember { mutableStateOf(0) }
    var selectedSecondTab by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column {
            BigButton(
                text = "Button",
                onClick = {
                    println("클릭!!!!!!")
                }
            )
            Spacer(modifier = Modifier.height(36.dp))
            MediumButton(text = "Button")
            Spacer(modifier = Modifier.height(36.dp))
            SmallButton(text = "Button")
            Spacer(modifier = Modifier.height(36.dp))
            InputField("Label", text, onValueChange = { text = it }, placeholder = "Placeholder")
            Spacer(modifier = Modifier.height(36.dp))
            Tabs(listOf("Label", "Label"), selectedFirstTab, onTabSelected = { selectedFirstTab = it })
            Spacer(modifier = Modifier.height(36.dp))
            Tabs(listOf("Label", "Label", "Label"), selectedSecondTab, onTabSelected = { selectedSecondTab = it })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComponentsPreview() {
    Components()
}

