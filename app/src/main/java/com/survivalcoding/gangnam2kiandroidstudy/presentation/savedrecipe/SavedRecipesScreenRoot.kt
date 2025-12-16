package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication

@Composable
fun SavedRecipesScreenRoot(
    viewModel: SavedRecipesViewModel = viewModel(
        factory = SavedRecipesViewModel.Factory,
        extras = MutableCreationExtras().apply {
            set(
                ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY,
                LocalContext.current.applicationContext as AppApplication
            )
        }
    )
) {
    val state = viewModel.state.collectAsState()
    SavedRecipesScreen(
        state = state.value
    )
}