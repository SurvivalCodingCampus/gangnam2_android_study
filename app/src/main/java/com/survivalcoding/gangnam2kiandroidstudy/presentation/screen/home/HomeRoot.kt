package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.RecipeAppApplication

@Composable
fun HomeRoot(viewModel: HomeViewModel = viewModel(factory = HomeViewModel.factory(application = LocalContext.current.applicationContext as RecipeAppApplication))) {

    val homeState by viewModel.state.collectAsState()
    Log.d("HomeRoot", "homeState: ${homeState.selectedCategory}")

    HomeScreen(homeState) {
        Log.d("HomeScreen", "it: $it")
        viewModel.onSelectedCategory(it)
    }


}