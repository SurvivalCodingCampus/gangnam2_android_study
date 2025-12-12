package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeRoot(viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)) {

    val homeState by viewModel.state.collectAsState()
    Log.d("HomeRoot", "homeState: ${homeState.selectedCategory}")

    HomeScreen(homeState) {
        Log.d("HomeScreen", "it: $it")
        viewModel.onSelectedCategory(it)
    }


}