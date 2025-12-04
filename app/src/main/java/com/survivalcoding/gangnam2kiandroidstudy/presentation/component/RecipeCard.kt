package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.model.Recipes
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RecipeCard(recipe: Recipes) {
    Row(modifier = Modifier.height(150.dp)) {
        Spacer(
            modifier = Modifier
                .width(30.dp)
                .fillMaxHeight()
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            AsyncImage(
                model = /*recipe.image*/R.drawable.steak,
                contentDescription = "레시피",
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(shape = RoundedCornerShape(10.dp))
            )

            Column(modifier = Modifier) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                    Row(
                        modifier = Modifier
                            .size(width = 37.dp, height = 16.dp)


                            .background(
                                color = AppColors.secondary20,
                                shape = RoundedCornerShape(20.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.width(7.dp))
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "별점",
                            modifier = Modifier.size(width = 8.dp, height = 8.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))

                        Text(
                            text = "4.0",
                            modifier = Modifier,
                            style = AppTextStyles.smallerTextRegular
                        )
                        Spacer(modifier = Modifier.width(7.dp))


                    }
                }
                Row {
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            "traditional",
                            modifier = Modifier.size(width = 200.dp, height = 42.dp)

                            ,
                            style = AppTextStyles.smallTextBold.copy(color = AppColors.white)
                        )
                        Text(
                            "By Chef",
                            modifier = Modifier.size(width = 53.dp, height = 12.dp),
                            style = AppTextStyles.smallerTextRegular.copy(color = AppColors.white)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }


            }
        }
        Spacer(
            modifier = Modifier
                .width(30.dp)
                .fillMaxHeight()
        )
    }


}

@Preview(showBackground = true)
@Composable
fun RecipeCardPreview() {
    val recipe = Recipes(
        category = "Test",
        chef = "Test",
        id = 1,
        image = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
        name = "Test",
        rating = 4.0,
        time = "Test"
    )
    RecipeCard(recipe)

}