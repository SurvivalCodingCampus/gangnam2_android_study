package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        BigButton("Button", onClick = {
                            println("BigButton 눌림!!!")
                        })
                        Spacer(modifier = Modifier.size(12.dp))

                        BigButton("Disabled", enabled = false)
                        Spacer(modifier = Modifier.size(12.dp))

                        MediumButton("Button", onClick = {
                            println("MediumButton 눌림!!!")
                        })
                        Spacer(modifier = Modifier.size(12.dp))

                        MediumButton("Disabled", enabled = false)
                        Spacer(modifier = Modifier.size(12.dp))

                        SmallButton("Button", onClick = {
                            println("SmallButton 눌림!!!")
                        })
                        Spacer(modifier = Modifier.size(12.dp))

                        SmallButton("Disabled", enabled = false)


                        Spacer(modifier = Modifier.size(12.dp))

                        // Disabled
                        InputField(
                            value = "",
                            label = "Disabled",
                            onValueChange = {},
                            placeholderText = "Placeholder",
                            enabled = false
                        )

                        Spacer(modifier = Modifier.size(12.dp))


                        // default
                        InputField(
                            value = input,
                            label = "Default",
                            onValueChange = { input = it },
                            placeholderText = "Placeholder"
                        )

                        Spacer(modifier = Modifier.size(12.dp))

                        // filled
                        InputField(
                            value = input2,
                            label = "Filled",
                            onValueChange = { input2 = it },
                            placeholderText = "Placeholder"
                        )
                    }
                }
            }
        }
    }
}

