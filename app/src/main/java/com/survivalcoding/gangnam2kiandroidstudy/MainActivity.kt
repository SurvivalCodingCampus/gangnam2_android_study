package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.MediumButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.tabs.DualTabBar
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.tabs.TripleTabBar
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.textfields.InputField
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

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                        .windowInsetsPadding(WindowInsets.safeDrawing),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    item {
                        DualTabBar(
                            selectedIndex = selectedDualTab,
                            onTabSelected = { selectedDualTab = it }
                        )
                    }

                    item {
                        TripleTabBar(
                            selectedIndex = selectedTripleTab,
                            onTabSelected = { selectedTripleTab = it }
                        )
                    }

                    item {
                        BigButton("Button") {
                            println("BigButton 눌림!!!")
                        }
                    }

                    item { Spacer(modifier = Modifier.size(12.dp)) }

                    item {
                        BigButton("Disabled", enabled = false)
                    }

                    item { Spacer(modifier = Modifier.size(12.dp)) }

                    item {
                        MediumButton("Button") {
                            println("MediumButton 눌림!!!")
                        }
                    }

                    item { Spacer(modifier = Modifier.size(12.dp)) }

                    item {
                        MediumButton("Disabled", enabled = false)
                    }

                    item { Spacer(modifier = Modifier.size(12.dp)) }

                    item {
                        SmallButton("Button") {
                            println("SmallButton 눌림!!!")
                        }
                    }

                    item { Spacer(modifier = Modifier.size(12.dp)) }

                    item {
                        SmallButton("Disabled", enabled = false)
                    }

                    item { Spacer(modifier = Modifier.size(12.dp)) }

                    item {
                        InputField(
                            value = "",
                            label = "Disabled",
                            onValueChange = {},
                            placeholderText = "Placeholder",
                            enabled = false
                        )
                    }

                    item { Spacer(modifier = Modifier.size(12.dp)) }

                    item {
                        InputField(
                            value = input,
                            label = "Default",
                            onValueChange = { input = it },
                            placeholderText = "Placeholder"
                        )
                    }

                    item { Spacer(modifier = Modifier.size(12.dp)) }

                    item {
                        InputField(
                            value = input2,
                            label = "Filled",
                            onValueChange = { input2 = it },
                            placeholderText = "Placeholder"
                        )
                    }

                    item { Spacer(modifier = Modifier.size(100.dp)) }
                }
            }
        }
    }
}
