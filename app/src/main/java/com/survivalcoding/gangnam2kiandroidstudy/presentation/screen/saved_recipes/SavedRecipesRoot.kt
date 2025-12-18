package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedRecipesRoot(
    viewmodel: SavedRecipesViewModel = koinViewModel(), onRecipeClick: (Recipe) -> Unit = {}
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