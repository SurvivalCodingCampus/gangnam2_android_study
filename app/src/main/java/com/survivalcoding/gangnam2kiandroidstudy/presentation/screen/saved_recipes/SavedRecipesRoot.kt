package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.RecipeAppApplication
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

@Composable
fun SavedRecipesRoot(
    viewmodel: SavedRecipesViewModel = viewModel(
        factory = SavedRecipesViewModel.factory(
            application = LocalContext.current.applicationContext as RecipeAppApplication
        )
    ), onRecipeClick: (Recipe) -> Unit = {}
) {
    val state = viewmodel.state.collectAsState()

    SavedRecipesScreen(
        state = state.value, onBookMarkClick = {
            viewmodel.delete(it)
            Log.d("SavedRecipesRoot", "SavedRecipesRoot: $it")
        }, onRecipeClick = onRecipeClick
    )


}


@Preview(showBackground = true)
@Composable
private fun SavedRecipesRootPreview() {
    //SavedRecipesRoot()

}