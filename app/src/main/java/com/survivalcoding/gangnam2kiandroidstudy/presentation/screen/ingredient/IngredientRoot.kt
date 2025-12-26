package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import RecipeLinkDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.appbar.CustomAppTopBar
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.appbar.MoreAction
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors


@Composable
fun IngredientRoot(
    recipeId: Int,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: IngredientViewModel = hiltViewModel()

) {
    val state by viewModel.state.collectAsState()
    val clipboardManager = LocalClipboardManager.current

    var isMenuExpanded by remember { mutableStateOf(false) }
    var isShareDialogVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is IngredientEvent.NavigateBack -> {
                    onBack()
                }

                IngredientEvent.ShowShareDialog -> {
                    isShareDialogVisible = true
                }
            }
        }
    }

    // 최초 진입 시 로드
    LaunchedEffect(recipeId) {
        viewModel.loadIngredients(recipeId)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Scaffold(
            containerColor = AppColors.white,
            topBar = {
                CustomAppTopBar(
                    showBackButton = true,
                    showMoreButton = true,
                    onBackClick = onBack,
                    isMenuExpanded = isMenuExpanded,
                    onMoreClick = { isMenuExpanded = true },
                    onMenuDismiss = { isMenuExpanded = false },
                    onMoreAction = { action ->
                        isMenuExpanded = false
                        when (action) {
                            MoreAction.Share -> viewModel.onAction(IngredientAction.ShareClicked)
                            MoreAction.Rate -> viewModel.onAction(IngredientAction.RateClicked)
                            MoreAction.Review -> viewModel.onAction(IngredientAction.ReviewClicked)
                            MoreAction.Unsave -> viewModel.onAction(IngredientAction.UnsaveClicked)
                        }
                    },
                )
            }
        ) { innerPadding ->
            IngredientScreen(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 30.dp),
                state = state,
                onAction = viewModel::onAction
            )

            if (isMenuExpanded) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f))
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            isMenuExpanded = false
                        }
                )
            }

            if (isShareDialogVisible) {
                RecipeLinkDialog(
                    recipeLink = "app.recipe.co/recipe/${recipeId}",
                    onDismiss = { isShareDialogVisible = false },
                    onCopyClick = {
                        // 클립보드 복사 로직
                        clipboardManager.setText(AnnotatedString("app.recipe.co/recipe/${recipeId}"))
                        isShareDialogVisible = false
                    }
                )
            }
        }
    }
}

