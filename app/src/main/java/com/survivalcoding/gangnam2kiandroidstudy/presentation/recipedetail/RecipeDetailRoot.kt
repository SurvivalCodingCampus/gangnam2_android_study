package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail

import android.content.Intent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeDetailMenu
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeDetailRoot(
    viewModel: RecipeDetailViewModel = koinViewModel(),
    onNavigateUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                RecipeDetailEvent.NavigateUp -> onNavigateUp()
                is RecipeDetailEvent.ShowShare -> {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "${event.recipe.name}\nCheck out this recipe!"
                        )
                    }
                    context.startActivity(
                        Intent.createChooser(intent, "Share Recipe")
                    )
                }
                else -> Unit
            }
        }
    }
    RecipeDetailScreen(
        recipeDetailUiState = uiState,
        onAction = viewModel::onAction
    )
}