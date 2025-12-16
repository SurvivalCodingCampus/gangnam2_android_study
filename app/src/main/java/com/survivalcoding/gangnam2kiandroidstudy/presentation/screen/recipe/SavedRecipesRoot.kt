package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication

@Composable
fun SavedRecipesRoot(
    modifier: Modifier = Modifier,
    application: AppApplication =
        LocalContext.current.applicationContext as AppApplication
) {
    val viewModel: SavedRecipesViewModel = viewModel(
        factory = SavedRecipesViewModel.factory(application)
    )

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold { innerpadding ->
        SavedRecipesScreen(
            modifier = modifier
                .fillMaxSize()
                .padding(innerpadding)
                .padding(horizontal = 30.dp),
            state = state,
        )
    }
}
