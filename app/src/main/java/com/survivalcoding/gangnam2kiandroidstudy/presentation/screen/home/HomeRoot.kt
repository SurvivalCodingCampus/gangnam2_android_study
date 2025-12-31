package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import com.survivalcoding.gangnam2kiandroidstudy.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoot(
    viewModel: HomeViewModel = koinViewModel(),
    //onOpenRecipeDetail: (Int) -> Unit,
    onOpenSearch: () -> Unit,
    ) {
    val state = viewModel.state.collectAsState().value
    val profilePainter = painterResource(R.drawable.profile)

    LaunchedEffect(Unit) {
        viewModel.onAction(HomeAction.LoadHome)
    }

    HomeScreen(
        state = state,
        profilePainter = profilePainter,
        onAction = { action ->
            when (action) {
                HomeAction.ClickSearch -> onOpenSearch()
                else -> viewModel.onAction(action)
            }
        }
    )
}
