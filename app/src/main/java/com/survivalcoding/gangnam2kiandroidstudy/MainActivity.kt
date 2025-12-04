package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.MediumButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Tabs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp)
                ) {
                    BigButton("Button")
                    Spacer(modifier = Modifier.height(16.dp))

                    MediumButton("Button")
                    Spacer(modifier = Modifier.height(16.dp))

                    SmallButton("Button")
                    Spacer(modifier = Modifier.height(16.dp))

                    InputField("Label", "")
                    Spacer(modifier = Modifier.height(16.dp))

                    InputField("Label", "")
                    Spacer(modifier = Modifier.height(16.dp))

                    InputField("Label", "placeholder")

                    Spacer(modifier = Modifier.height(16.dp))

                    Tabs(
                        labels = listOf("Label", "Label"),
                        selectedIndex = 1,
                        onValueChange = {},
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Tabs(
                        labels = listOf("Label", "Label"),
                        selectedIndex = 0,
                        onValueChange = {},
                    )
                }
            }
        }
    }
}