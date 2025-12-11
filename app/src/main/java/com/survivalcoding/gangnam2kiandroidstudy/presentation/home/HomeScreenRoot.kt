package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.R

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel = viewModel(
        factory = HomeViewModel.Factory
    )
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        state = state,
        onSelectCategory = {
            viewModel.onSelectCategory(it)
        },
        profilePainter = painterResource(R.drawable.profile)
    )

}