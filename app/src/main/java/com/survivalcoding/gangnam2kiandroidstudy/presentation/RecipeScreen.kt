package com.survivalcoding.gangnam2kiandroidstudy.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.data.model.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeIngredient
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.FilterButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.MediumButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.RatingButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.dialog.RateDialog
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.tabs.DualTabBar

@Composable
fun RecipeScreen(modifier: Modifier = Modifier) {
    var selectedDualTab by remember { mutableStateOf(0) }

    // TODO: 실제 JSON 로드 필요 (지금은 Fake Data)
    val ingredients = listOf(
        IngredientItem(
            1,
            "Tomatos",
            "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg"
        ),
        IngredientItem(
            9,
            "Onion",
            "https://cdn.pixabay.com/photo/2013/02/21/19/14/onion-bulbs-84722_1280.jpg"
        ),
        IngredientItem(
            8,
            "Pepper",
            "https://cdn.pixabay.com/photo/2016/03/05/22/31/pepper-1239308_1280.jpg"
        )
    )

    val recipeIngredients = listOf(
        RecipeIngredient(1, 1, 500),
        RecipeIngredient(1, 9, 50),
        RecipeIngredient(1, 8, 10)
    )

    val filteredIngredients = recipeIngredients.filter { it.recipeId == 1 }

    // 다이얼로그 상태
    var showDialog by remember { mutableStateOf(false) }
    var rating by remember { mutableStateOf(0) }


    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            DualTabBar(
                leftTab = "Ingredients",
                rightTab = "Procedure",
                selectedIndex = selectedDualTab,
                onTabSelected = { selectedDualTab = it })
        }

        item {
            RecipeCard(
                name = "Traditional spare ribs baked",
                imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
                chef = "Chef John",
                time = "20 min",
                rating = 4.0,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            )
        }


        items(filteredIngredients.size) { index ->
            val recIng = filteredIngredients[index]
            val ingredient = ingredients.first { it.id == recIng.ingredientId }

            IngredientItem(
                name = ingredient.name,
                imageUrl = ingredient.image,
                amount = recIng.amount,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
            )

        }

        item {
            var selectedRating: Int? by remember { mutableStateOf(null) }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                (5 downTo 1).forEach { value ->
                    RatingButton(
                        rate = value.toString(),
                        isSelected = selectedRating == value,
                        onClick = {
                            selectedRating = if (selectedRating == value) {
                                null   // 같은 버튼을 다시 누르면 선택 해제
                            } else {
                                value  // 선택
                            }
                        }
                    )
                }
            }
        }


        item { Spacer(modifier = Modifier.size(10.dp)) }

        item {
            val filterOptions = listOf("All", "Popular", "Soup", "Category Name")
            var selectedStates by rememberSaveable {
                mutableStateOf(List(filterOptions.size) { false })
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                filterOptions.forEachIndexed { index, text ->
                    FilterButton(
                        text = text,
                        isSelected = selectedStates[index],
                        onClick = {
                            selectedStates = selectedStates.toMutableList().also {
                                it[index] = !it[index]
                            }
                        }
                    )
                }

            }
        }
        item { Spacer(modifier = Modifier.size(10.dp)) }
        item { BigButton("Big") }
        item { Spacer(modifier = Modifier.size(10.dp)) }
        item { MediumButton("Medium", modifier = Modifier.padding(horizontal = 30.dp)) }
        item { Spacer(modifier = Modifier.size(10.dp)) }
        item { SmallButton("Small", modifier = Modifier.size(85.dp, 43.dp)) }

        item { Spacer(modifier = Modifier.size(10.dp)) }


        item {
            BigButton(
                text = "Rate Recipe",
                onClick = { showDialog = true }
            )
        }
    }

    if (showDialog) {
        RateDialog(
            modifier = Modifier.padding(horizontal = 102.dp),
            title = "Rate recipe",
            actionName = "Send",
            onChange = {
                rating = it
                showDialog = false  // 평가 완료 후 닫기
            },
            onDismiss = { showDialog = false }
        )
    }
}