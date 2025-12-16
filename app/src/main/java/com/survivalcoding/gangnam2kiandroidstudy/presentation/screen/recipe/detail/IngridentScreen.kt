package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingrident
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun IngridentScreen(
    ingridents: List<Ingrident>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row {
                Icon(
                    painter = painterResource(R.drawable.recipe_serve),
                    contentDescription = "Recipe Serve"
                )
                Spacer(Modifier.width(5.dp))
                Text(
                    text = "1 serve",
                    style = AppTextStyles.smallerTextRegular,
                    color = AppColors.gray3
                )
            }

            Spacer(Modifier.weight(1f))
            Text(
                text = "${ingridents.size} Items",
                style = AppTextStyles.smallerTextRegular,
                color = AppColors.gray3
            )
        }

        Spacer(Modifier.height(20.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(ingridents) { ingredient ->
                IngredientItem(ingrident = ingredient)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun IngridentScreenPreview() {
    val ingridents = listOf(
        Ingrident(
            1,
            "Tomatos",
            "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
            "500g",
            1
        ),
        Ingrident(
            2,
            "Tomatos",
            "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
            "500g",
            1
        ),
        Ingrident(
            3,
            "Tomatos",
            "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
            "500g",
            1
        )
    )

    IngridentScreen(
        ingridents = ingridents,
        modifier = Modifier.padding(30.dp)
    )
}