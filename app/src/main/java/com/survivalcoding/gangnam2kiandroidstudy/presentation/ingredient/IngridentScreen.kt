package com.survivalcoding.gangnam2kiandroidstudy.presentation.ingredient

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingrident
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.ProcedureItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Tabs2
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun IngredientScreen(
    state: IngridentState = IngridentState(),
    modifier: Modifier = Modifier,
    onValueChange: (Int) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.white)
    ) {
        Spacer(modifier = Modifier.height(44.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "back",
                modifier = Modifier.size(20.dp)
            )
            Icon(
                imageVector = Icons.Default.MoreHoriz,
                contentDescription = "more",
                modifier = Modifier.size(20.dp)
            )
        }
        RecipeCard(
            recipe = state.recipe,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = state.recipe?.title ?: "",
                style = AppTextStyles.smallTextBold,
                modifier = Modifier.width(194.dp)

            )
            Text(
                text = "(13k Reviews)",
                style = AppTextStyles.smallerTextRegular.copy(
                    fontSize = 14.sp,
                    color = AppColors.gray3
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ingrdeintimage),
                        contentDescription = "profile image",
                        modifier = Modifier.size(40.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = state.recipe?.chef ?: "",
                        style = AppTextStyles.smallTextBold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.bold_location),
                            contentDescription = "location",
                            tint = AppColors.primary80,
                            modifier = Modifier.size(17.dp)
                        )
                        Spacer(modifier = Modifier.width(1.dp))
                        Text(
                            text = state.recipe?.address ?: "",
                            style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                        )
                    }
                }
            }

            SmallButton(
                text = "Follow",
                modifier = Modifier.width(85.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Tabs2(
            labels = listOf("Ingredient", "Procedure"),
            selectedIndex = state.selectedIndex,
            onValueChange = {
                onValueChange(it)
            }
        )

        Spacer(modifier = Modifier.height(22.dp))
        if (state.selectedIndex == 0) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.serve),
                        contentDescription = "ingredient",
                        tint = AppColors.gray3
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "1 serve",
                        style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                    )
                }
                Text(
                    text = "${state.ingridents.size} Items",
                    style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.ingridents) { ingredient ->
                    IngredientItem(
                        imageUrls = ingredient.image,
                        name = ingredient.name,
                        weight = "${ingredient.amount}g"
                    )
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.serve),
                        contentDescription = "ingredient",
                        tint = AppColors.gray3
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "1 serve",
                        style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                    )
                }
                Text(
                    text = "${state.procedures.size} Steps",
                    style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.procedures) {
                    ProcedureItem(
                        procedure = it
                    )
                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
private fun IngredientScreenPreview() {
    IngredientScreen(
        state = IngridentState(
            isLoading = false,
            recipe = Recipe(
                title = "Spicy chicken burger with French fries",
                imageUrls = "https://cdn.pixabay.com/photo/201",
                chef = "Chef John",
                category = "Chinese",
                rating = 4.0,
                time = "20 min",
                createdAt = System.currentTimeMillis(),
                id = 0,
                address = "Seoul"
            ),
            selectedIndex = 1,
            ingridents = listOf(
                Ingrident(
                    id = 1,
                    name = "Tomato",
                    amount = 500,
                    image = "https://cdn.pixabay.com/photo/2017/10/06/17",
                ),
                Ingrident(
                    id = 1,
                    name = "Tomato",
                    amount = 500,
                    image = "https://cdn.pixabay.com/photo/2017/10/06/17",
                ),
                Ingrident(
                    id = 1,
                    name = "Tomato",
                    amount = 500,
                    image = "https://cdn.pixabay.com/photo/2017/10/06/17",
                )
            ),
            procedures = listOf(
                Procedure(
                    recipeId = 1,
                    step = 1,
                    content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed,Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed,Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed,"
                )
            )
        )
    )
}