package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun HomeRoot(viewModel: HomeViewModel = hiltViewModel()) {

    val homeState by viewModel.state.collectAsState()
    Log.d("HomeRoot", "homeState: ${homeState.selectedCategory}")

    HomeScreen(homeState) {
        Log.d("HomeScreen", "it: $it")
        viewModel.onSelectedCategory(it)
    }


}