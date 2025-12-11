package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes.SearchRecipesScreen
import kotlinx.coroutines.FlowPreview

@ExperimentalMaterial3Api
@FlowPreview
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SearchRecipesScreen()
        }
    }
}