package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.NavigationRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.util.KeyboardVisibilityHandler

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            KeyboardVisibilityHandler {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.safeDrawing)
                ) {
                    NavigationRoot(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}
