package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SavedRecipesScreen(viewModel: SavedRecipesViewModel = viewModel(factory = SavedRecipesViewModel.Factory)) {
    val savedRecipe by viewModel.savedRecipes.collectAsState()
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            Spacer(modifier = Modifier.height(54.dp))
            Text(
                "Saved Recipes",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = AppTextStyles.mediumTextBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(savedRecipe) { recipe ->
                    RecipeCard(
                        recipe = recipe
                    )

                }
            }
            Spacer(modifier = Modifier.height(92.dp))
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(R.drawable.outline_home),
                colorFilter = ColorFilter.tint(AppColors.gray4),
                contentDescription = "Home",
                modifier = Modifier
                    .weight(1f)
            )
            Image(
                painter = painterResource(R.drawable.inactive),
                colorFilter = ColorFilter.tint(AppColors.primary100),


                contentDescription = "inactive",
                modifier = Modifier
                    .weight(1f)
                    .size(24.dp)
            )
            Image(
                painter = painterResource(R.drawable.outline_notification_bing),
                colorFilter = ColorFilter.tint(AppColors.gray4),

                contentDescription = "notification",
                modifier = Modifier.weight(1f)

            )
            Image(
                painter = painterResource(R.drawable.outline_profile),
                colorFilter = ColorFilter.tint(AppColors.gray4),
                contentDescription = "profile",
                modifier = Modifier
                    .weight(1f)
            )

        }
        Spacer(modifier = Modifier.height(58.dp))
    }

}

@Preview(showBackground = true)
@Composable
fun SavedRecipesScreenPreview() {
    val recipeDto = Recipe(
        category = "Test",
        chef = "Chef John",
        id = 1,
        image = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
        name = "Grilled Steak",
        rating = 4.5,
        time = "30 min" // 예시 시간 추가
    )
    SavedRecipesScreen()
}