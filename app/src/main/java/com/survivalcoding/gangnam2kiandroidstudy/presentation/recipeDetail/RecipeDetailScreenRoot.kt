package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipeDetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.ShareDialog
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeDetailScreenRoot(
    recipeId: Int,
    viewModel: RecipeDetailViewModel = koinViewModel(),
    onBackClick: () -> Unit = {}
) {
    viewModel.loadRecipeDetail(recipeId)

    val state = viewModel.state.collectAsState()

    var isDropDownExpanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    RecipeDetailScreen(
        state = state.value,
        isDropDownExpanded = isDropDownExpanded,
        onAction = { action ->
            when(action) {
                is RecipeDetailAction.OnValueChange -> {
                    viewModel.onAction(action)
                }
                RecipeDetailAction.OnDropDownClick -> {
                    isDropDownExpanded = true
                }

                RecipeDetailAction.OnDropDownDismiss -> {
                    isDropDownExpanded = false
                }

                RecipeDetailAction.OnShareClick -> {
                    isDropDownExpanded = false
                    showDialog = true
                }

            }
        },
        onBackClick = onBackClick
    )

    if (showDialog) {
        ShareDialog(
            onDismiss = {
                showDialog = false
            },
            onCopy = { link ->
                viewModel.onCopyClick(link)
                showDialog = false
            }
        )
    }
}