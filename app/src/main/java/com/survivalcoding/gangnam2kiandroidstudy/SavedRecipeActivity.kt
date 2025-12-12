package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.RecipeHomeRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search.SearchRecipeRoot
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.Gangnam2kiAndroidStudyTheme

class SavedRecipeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Gangnam2kiAndroidStudyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    SavedRecipeScreen(modifier = Modifier.padding(innerPadding))
//                    SearchRecipeRoot(modifier = Modifier.padding(innerPadding))
                    RecipeHomeRoot(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
