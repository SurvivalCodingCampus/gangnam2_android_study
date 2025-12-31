package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles
import kotlinx.collections.immutable.ImmutableList

@Composable
fun IngredientScreen(
    ingredients: ImmutableList<Ingredient>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {

        // 상단 요약 영역
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_serve),
                    contentDescription = null,
                    tint = AppColors.gray3
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "1 serve",
                    style = AppTextStyles.smallerTextRegular,
                    color = AppColors.gray3
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "${ingredients.size} Items",
                style = AppTextStyles.smallerTextRegular,
                color = AppColors.gray3
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 재료 리스트
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = ingredients,
                key = { it.id }
            ) { ingredient ->
                IngredientItem(
                    imageURL = ingredient.image,
                    ingredientName = ingredient.name,
                    ingredientWeight = "200g"
                )
            }
        }
    }
}
