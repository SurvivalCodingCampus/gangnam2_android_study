package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredients
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
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
    procedure: List<Procedure>,
    onValueChanged: (Boolean) -> Unit,
    onBackButtonClicked: () -> Unit,
    onMoreButtonClicked: (Boolean) -> Unit,
    onShareDialogRequest: (Boolean) -> Unit,
    onCopyLink: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            Spacer(modifier = Modifier.height(54.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.outline_arrow_left),
                    contentDescription = "뒤로가기 버튼",
                    modifier = Modifier.clickable(onClick = { onBackButtonClicked() })
                )

                Box {
                    Icon(
                        painter = painterResource(R.drawable.outline_more),
                        contentDescription = "more버튼",
                        modifier = Modifier.clickable(onClick = { onMoreButtonClicked(true) })
                    )
                    DropdownMenu(
                        expanded = state.isDropDownMenuShow,
                        onDismissRequest = { onMoreButtonClicked(false) }
                    ) {
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.bold_share),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = AppColors.black
                                )
                            },
                            text = { Text("Share") },
                            onClick = {
                                onMoreButtonClicked(false)
                                onShareDialogRequest(true)
                            }
                        )
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.bold_star),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = AppColors.black
                                )
                            },
                            text = { Text("Rate Recipe") },
                            onClick = { /* Do something... */ }
                        )
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.bold_message),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = AppColors.black
                                )
                            },
                            text = { Text("Review") },
                            onClick = { /* Do something... */ }
                        )
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.bold_bookmark_inactive),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = AppColors.black
                                )
                            },
                            text = { Text("Unsave") },
                            onClick = { /* Do something... */ }
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(54.dp))

            RecipeCard(
                recipe = state.recipe.copy(name = "", chef = ""),
                isSaved = true,
                onBookMarkClick = {}
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.height(41.dp)) {
                Text(
                    state.recipe.name,
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
            Profile(
                name = state.recipe.chef,
                region = "south korea",
                imageUrl = state.recipe.image
            ) //TODO지역 수정)
            Spacer(modifier = Modifier.height(8.dp))
            Tab2(
                listOf("Ingredient", "Procedure"), if (state.isSelectIngredient) 0 else 1
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
                    if (state.isSelectIngredient) "${state.recipe.ingredients.size} Items" else "${procedure.size} Steps",
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
                    items(state.recipe.ingredients) { recipe ->
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

        // 통합 Scrim (메뉴 또는 다이얼로그가 떠있을 때)
        if (state.isDropDownMenuShow || state.isShareDialogShow) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .zIndex(1f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        if (state.isDropDownMenuShow) onMoreButtonClicked(false)
                        if (state.isShareDialogShow) onShareDialogRequest(false)
                    }
            )
        }

        // Custom Dialog Overlay
        if (state.isShareDialogShow) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(2f)
                    .padding(horizontal = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Recipe Link",
                                style = AppTextStyles.largeTextBold.copy(fontWeight = FontWeight.SemiBold),
                                modifier = Modifier.padding(top = 5.dp)
                            )
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = AppColors.gray1,
                                modifier = Modifier
                                    .size(5.dp)
                                    .clickable { onShareDialogRequest(false) }
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Copy recipe link and share your recipe link with friends and family.",
                            style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray2)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(43.dp)
                                .background(
                                    color = AppColors.gray4,
                                    shape = RoundedCornerShape(9.dp)
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "app.Recipe.co/recipe?id=${state.recipe.id}",
                                style = AppTextStyles.smallTextRegular.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 11.sp
                                ),
                                modifier = Modifier
                                    .padding(start = 14.dp)
                                    .weight(1f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Button(
                                onClick = { onCopyLink("app.Recipe.co/recipe?id=${state.recipe.id}") },
                                shape = RoundedCornerShape(9.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = AppColors.primary100),
                                modifier = Modifier
                                    .height(43.dp)
                            ) {
                                Text(
                                    text = "Copy Link",
                                    style = AppTextStyles.smallerTextBold.copy(color = Color.White)
                                )
                            }
                        }
                    }
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
        isSaved = true,
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
        procedure = mockProcedure,
        {},
        {},
        {},
        {},
        {}
    )
}