package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes.SearchRecipesScreen
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.Gangnam2kiAndroidStudyTheme
import org.koin.android.ext.android.inject

class SearchRecipesActivity : ComponentActivity() {
    // 아무데서나 하면 안돼 (Activity, Test 코드에서는 OK)
    val repository: RecipeRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("SearchRecipesActivity : ${repository.hashCode()}")

        enableEdgeToEdge()
        setContent {
            SearchRecipesScreen()
        }
    }
}
