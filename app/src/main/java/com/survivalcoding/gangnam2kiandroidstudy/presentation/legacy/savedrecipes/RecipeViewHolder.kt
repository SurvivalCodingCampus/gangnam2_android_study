package com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.savedrecipes

import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard

class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val composeView: ComposeView = itemView.findViewById(R.id.savedRecipeCard)

    init {
        /*
        Fragment 가 onDestroyView 될 때 ComposeView 메모리 해제
         */
        composeView.setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed,
        )
    }

    fun bind(recipe: Recipe, listener: SavedRecipesActionListener) {
        /*
        RecipeCard 사용하기 위한 ComposeView 세팅
         */
        composeView.setContent {
            RecipeCard(
                recipe = recipe,
                onClick = listener::onCardClick,
                onBookmarkClick = listener::onBookmarkClick,
            )
        }
    }
}