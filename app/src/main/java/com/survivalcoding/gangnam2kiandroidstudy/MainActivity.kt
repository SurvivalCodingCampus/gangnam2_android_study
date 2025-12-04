package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.RecipeScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.util.KeyboardVisibilityHandler

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KeyboardVisibilityHandler {
                var input by remember { mutableStateOf("") }
                var input2 by remember { mutableStateOf("") }
                var selectedDualTab by remember { mutableStateOf(0) }
                var selectedTripleTab by remember { mutableStateOf(0) }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.safeDrawing) // 상단 시스템 바 침범하지 않도록
                        .padding(horizontal = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    RecipeScreen()


//                    item {
//                        DualTabBar(
//                            selectedIndex = selectedDualTab,
//                            onTabSelected = { selectedDualTab = it }
//                        )
//                    }
//
//                    item {
//                        TripleTabBar(
//                            selectedIndex = selectedTripleTab,
//                            onTabSelected = { selectedTripleTab = it }
//                        )
//                    }
//
//                    item {
//                        BigButton("Button") {
//                            println("BigButton 눌림!!!")
//                        }
//                    }
//
//                    item { Spacer(modifier = Modifier.size(12.dp)) }
//
//                    item {
//                        BigButton("Button") {
//                            println("BigButton 눌림!!!")
//                        }
//                    }
//
//                    item { Spacer(modifier = Modifier.size(12.dp)) }
//
//                    item {
//                        BigButton("Disabled", enabled = false)
//                    }
//
//                    item { Spacer(modifier = Modifier.size(12.dp)) }
//
//                    item {
//                        MediumButton("Button") {
//                            println("MediumButton 눌림!!!")
//                        }
//                    }
//
//                    item { Spacer(modifier = Modifier.size(12.dp)) }
//
//                    item {
//                        MediumButton("Button") {
//                            println("MediumButton 눌림!!!")
//                        }
//                    }
//
//                    item { Spacer(modifier = Modifier.size(12.dp)) }
//
//                    item {
//                        MediumButton("Disabled", enabled = false)
//                    }
//
//                    item { Spacer(modifier = Modifier.size(12.dp)) }
//
//                    item {
//                        SmallButton("Button", modifier = Modifier.size(59.dp, 35.dp)) {
//                            println("SmallButton 눌림!!!")
//                        }
//                    }
//
//                    item { Spacer(modifier = Modifier.size(12.dp)) }
//
//                    item {
//                        SmallButton("Button", modifier = Modifier.size(59.dp, 35.dp)) {
//                            println("SmallButton 눌림!!!")
//                        }
//                    }
//
//                    item { Spacer(modifier = Modifier.size(12.dp)) }
//
//                    item {
//                        SmallButton(
//                            "Disabled",
//                            modifier = Modifier.size(85.dp, 43.dp),
//                            enabled = false
//                        )
//                    }
//
//                    item { Spacer(modifier = Modifier.size(12.dp)) }
//
//                    item {
//                        InputField(
//                            value = "",
//                            label = "Disabled",
//                            onValueChange = {},
//                            placeholderText = "Placeholder",
//                            enabled = false
//                        )
//                    }
//
//                    item { Spacer(modifier = Modifier.size(12.dp)) }
//
//                    item {
//                        InputField(
//                            value = input,
//                            label = "Default",
//                            onValueChange = { input = it },
//                            placeholderText = "Placeholder"
//                        )
//                    }
//
//                    item { Spacer(modifier = Modifier.size(12.dp)) }
//
//                    item {
//                        InputField(
//                            value = input2,
//                            label = "Filled",
//                            onValueChange = { input2 = it },
//                            placeholderText = "Placeholder"
//                        )
//                    }
//
//                    item { Spacer(modifier = Modifier.size(100.dp)) }
                }
            }
        }
    }
}
