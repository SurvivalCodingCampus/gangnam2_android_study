package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.appbar.CustomAppTopBar
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors

@Composable
fun IngredientRoot(
    recipeId: Int,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: IngredientViewModel = hiltViewModel()

) {
    val state by viewModel.state.collectAsState()

    // 최초 진입 시 로드
    LaunchedEffect(recipeId) {
        viewModel.loadIngredients(recipeId)
    }

    Scaffold(
        containerColor = AppColors.white,
        topBar = {
            CustomAppTopBar(
                showBackButton = true,
                showMoreButton = true,
                onBackClick = onBack
            )
        }
    ) { innerPadding ->
        IngredientScreen(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 30.dp),
            state = state,
            onTabSelected = viewModel::onTabSelected,
            onFollowClick = {
                // TODO: Follow 기능 연결
            },
        )
    }
}
