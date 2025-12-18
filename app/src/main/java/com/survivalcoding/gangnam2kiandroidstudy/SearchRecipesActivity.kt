package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes.SearchRecipesRoot
import org.koin.android.ext.android.inject

class SearchRecipesActivity : ComponentActivity() {
    // 아무데서나 하면 안돼 (Activity, Test 코드에서는 OK)
    val repository: RecipeRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("SearchRecipesActivity : ${repository.hashCode()}")

        enableEdgeToEdge()
        setContent {
            SearchRecipesRoot()
        }
    }
}
