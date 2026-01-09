package com.survivalcoding.gangnam2kiandroidstudy.recipereader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.survivalcoding.gangnam2kiandroidstudy.recipereader.data.repository.ReaderRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.recipereader.presentation.ReaderViewModel
import com.survivalcoding.gangnam2kiandroidstudy.recipereader.presentation.RecipeListScreen

class MainActivity : ComponentActivity() {
    
    // Manual Dependency Injection
    private val viewModel: ReaderViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(ReaderViewModel::class.java)) {
                    val repository = ReaderRepositoryImpl(applicationContext)
                    @Suppress("UNCHECKED_CAST")
                    return ReaderViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeListScreen(viewModel = viewModel)
                }
            }
        }
    }
}
