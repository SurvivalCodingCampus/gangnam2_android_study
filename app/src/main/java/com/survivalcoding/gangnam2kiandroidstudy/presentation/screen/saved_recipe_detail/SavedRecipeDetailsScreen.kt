package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredients
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedures
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.ProcedureItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Profile
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Tab2
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppTextStyles

@Composable
fun SavedRecipeItemScreen(
    state: SavedRecipeDetailsState,
    recipe: Recipe,
    procedure: List<Procedure>,
    onValueChanged: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        Spacer(modifier = Modifier.height(88.dp))
        RecipeCard(
            recipe = recipe.copy(name = "", chef = ""),
            isSaved = true,
            onBookMarkClick = {}
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.height(41.dp)) {
            Text(
                recipe.name,
                modifier = Modifier.weight(1f),
                style = AppTextStyles.smallTextBold.copy(fontWeight = FontWeight.SemiBold)
            )
            Text(
                text = "13k Reviewers",
                modifier = Modifier.padding(start = 18.dp, end = 5.dp),
                style = AppTextStyles.smallTextBold.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = AppColors.gray4
                )
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Profile(name = recipe.chef, region = "south korea", imageUrl = recipe.image) //TODO지역 수정)
        Spacer(modifier = Modifier.height(8.dp))
        Tab2(
            listOf("Ingredient", "Procedure"), if(state.isSelectIngredient) 0 else 1
        ) {
            if (it == 0) {
                onValueChanged(true)
            } else {
                onValueChanged(false)
            }
        }
        Spacer(modifier = Modifier.height(43.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier) {
                Icon(
                    painter = painterResource(R.drawable.icon_serve),
                    contentDescription = "1인분 아이콘",
                    tint = AppColors.gray3,
                    modifier = Modifier.size(17.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    "1 serve",
                    modifier = Modifier,
                    style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                )
            }
            Text(
                if (state.isSelectIngredient) "${recipe.ingredients.size} Items" else "${procedure.size} Steps",
                modifier = Modifier,
                style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (state.isSelectIngredient) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(recipe.ingredients) { recipe ->
                    IngredientItem(
                        imageUrl = recipe.ingredient.image,
                        foodName = recipe.ingredient.name,
                        foodGram = recipe.amount
                    )

                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 10.dp)
            ) {
                items(procedure) {
                    ProcedureItem(it)
                }


            }
        }


    }

}

@Preview(showBackground = true)
@Composable
private fun SavedRecipeItemScreenPreview() {
    val mockRecipe = Recipe(
        category = "Indian",
        chef = "Chef John",
        id = 1,
        image = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
        name = "Traditional spare ribs baked",
        rating = 4.0,
        time = "20 min",
        ingredients = listOf(
            Ingredients(
                amount = 500,
                ingredient = Ingredient(
                    id = 3,
                    name = "Pork",
                    image = "https://cdn.pixabay.com/photo/2019/12/20/14/44/meat-4708596_1280.jpg"
                )
            ),
            Ingredients(
                amount = 50,
                ingredient = Ingredient(
                    id = 9,
                    name = "Onion",
                    image = "https://cdn.pixabay.com/photo/2013/02/21/19/14/onion-bulbs-84722_1280.jpg"
                )
            ),
            Ingredients(
                amount = 10,
                ingredient = Ingredient(
                    id = 8,
                    name = "Pepper",
                    image = "https://cdn.pixabay.com/photo/2016/03/05/22/31/pepper-1239308_1280.jpg"
                )
            ),
            Ingredients(
                amount = 100,
                ingredient = Ingredient(
                    id = 1,
                    name = "Tomato",
                    image = "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg"
                )
            )
        )
    )
    val mockProcedure = listOf(
        Procedure(
            recipeId = 1,
            step = 1,
            content = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?\n" +
                    "Tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        )
    )
    SavedRecipeItemScreen(
        state = SavedRecipeDetailsState(isSelectIngredient = false),
        recipe = mockRecipe,
        procedure = mockProcedure,
        { 0 }
    )
}