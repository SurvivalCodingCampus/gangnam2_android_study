package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import com.survivalcoding.gangnam2kiandroidstudy.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoot(
    onSearchClick: () -> Unit,
) {
    val viewModel: HomeViewModel = koinViewModel()
    val state = viewModel.state.collectAsState().value
    val profilePainter = painterResource(R.drawable.profile)

    HomeScreen(
        state = state,
        onSelectCategory = viewModel::onSelectCategory,
        profilePainter = profilePainter,
        onSearchClick = onSearchClick,
    )
}
