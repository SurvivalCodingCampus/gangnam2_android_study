package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.MOCK
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipes
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeAction
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppTextStyles
import kotlinx.serialization.json.Json

@Composable
fun NewRecipesCard(recipe: Recipe, onAction: (HomeAction) -> Unit) {
    Box(
        modifier = Modifier
            .size(width = 251.dp, height = 127.dp)
            .clickable(onClick = { onAction(HomeAction.OnRecipeItemClicked(recipe = recipe)) })
    ) {
        Column(modifier = Modifier) {
            Spacer(modifier = Modifier.height(32.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = AppColors.white, shape = RoundedCornerShape(10.dp))
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 9.3.dp)
                        .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Text(
                            text = recipe.name,
                            style = AppTextStyles.smallerTextBold.copy(
                                color = AppColors.gray1,
                                fontWeight = FontWeight.SemiBold
                            ),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = Modifier.weight(1f)

                        )
                        Spacer(modifier = Modifier.width(102.26.dp))
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(1.86.dp)
                    ) {
                        Image(
                            painter = if (recipe.rating >= 1.0) painterResource(R.drawable.star) else painterResource(
                                R.drawable.star_5
                            ),
                            contentDescription = "별점",
                            modifier = Modifier.size(width = 10.46.dp, height = 10.5.dp),
                        )
                        Image(
                            painter = if (recipe.rating >= 2.0) painterResource(R.drawable.star) else painterResource(
                                R.drawable.star_5
                            ),
                            contentDescription = "별점",
                            modifier = Modifier.size(width = 10.46.dp, height = 10.5.dp),
                        )
                        Image(
                            painter = if (recipe.rating >= 3.0) painterResource(R.drawable.star) else painterResource(
                                R.drawable.star_5
                            ),
                            contentDescription = "별점",
                            modifier = Modifier.size(width = 10.46.dp, height = 10.5.dp),
                        )
                        Image(
                            painter = if (recipe.rating >= 4.0) painterResource(R.drawable.star) else painterResource(
                                R.drawable.star_5
                            ),
                            contentDescription = "별점",
                            modifier = Modifier.size(width = 10.46.dp, height = 10.5.dp),
                        )
                        Image(
                            painter = if (recipe.rating >= 5.0) painterResource(R.drawable.star) else painterResource(
                                R.drawable.star_5
                            ),
                            contentDescription = "별점",
                            modifier = Modifier.size(width = 10.46.dp, height = 10.5.dp),
                        )


                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(modifier = Modifier.size(25.dp)) {
                            //AsyncImage(model = R.drawable.new_recipe_chef_img, contentDescription = "셰프사진",modifier = Modifier.clip(shape = CircleShape), contentScale = ContentScale.Crop)
                            Image(
                                painter = painterResource(R.drawable.new_recipe_chef_img),
                                contentDescription = "셰프사진",
                                modifier = Modifier.clip(shape = CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Row(
                            modifier = Modifier.weight(1f),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = recipe.chef,
                                style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                            )
                            Row(modifier = Modifier) {
                                Icon(
                                    painter = painterResource(R.drawable.timer),
                                    contentDescription = "시간",
                                    modifier = Modifier.size(17.dp),
                                    tint = AppColors.gray3
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = recipe.time + "s",
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))


                }
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth()

        ) {
            AsyncImage(
                model = recipe.image,
                contentDescription = "음식사진",
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .align(Alignment.TopEnd)
                    .size(width = 79.95.dp, height = 86.dp),
                contentScale = ContentScale.Crop
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun NewRecipesCardPreview() {
    val mock = Json.decodeFromString<Recipes>(MOCK)
    NewRecipesCard(mock.recipes[0]) {}

}