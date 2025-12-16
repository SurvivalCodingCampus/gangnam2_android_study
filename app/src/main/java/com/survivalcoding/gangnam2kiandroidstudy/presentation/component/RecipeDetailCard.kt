package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.mockdata.MockRecipeData
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RecipeDetailCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    onUnBookmark: (Int) -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        val painter = if (LocalInspectionMode.current) {
            painterResource(R.drawable.recipe_main)
        } else {
            rememberAsyncImagePainter(recipe.image)
        }

        Image(
            painter = painter,
            contentDescription = "${recipe.name} image",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.Transparent)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(10.dp)
            ) {
                Spacer(Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .size(37.dp, 16.dp)
                        .background(color = AppColors.secondary20, shape = RoundedCornerShape(20.dp)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.star_1),
                        contentDescription = "star icon",
                        modifier = Modifier
                            .size(8.dp),
                        tint = AppColors.rating
                    )
                    Spacer(Modifier.width(3.dp))
                    Text(text = "${recipe.rating}", style = AppTextStyles.smallerTextRegular)
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.Bottom,
            ) {
                Spacer(Modifier.weight(1f))

                Row(
                    modifier = Modifier.height(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.timer),
                        contentDescription = "timer icon",
                        modifier = Modifier.size(17.dp),
                        tint = AppColors.gray4
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(text = recipe.time, style = AppTextStyles.smallerTextRegular, color = AppColors.gray4)
                }
                Spacer(Modifier.width(10.dp))

                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onUnBookmark(recipe.id) }
                        .background(color = AppColors.white, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.union),
                        contentDescription = "union icon",
                        modifier = Modifier.size(16.dp),
                        tint = AppColors.primary80
                    )
                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeDetailCardPreview() {
    val recipe = MockRecipeData.recipeListOne

    Scaffold {
        RecipeDetailCard(
            modifier = Modifier.padding(it),
            recipe = recipe
        )
    }
}
