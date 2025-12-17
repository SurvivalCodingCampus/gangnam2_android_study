package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.R

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel = hiltViewModel()
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