package com.survivalcoding.gangnam2kiandroidstudy.presentation.ingredient

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredient
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.ProcedureStepItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun IngredientScreen(
    state: IngredientState,
    onAction: (IngredientAction) -> Unit,
    onBack: () -> Unit
) {
    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = AppColors.primary100)
        }
        return
    }

    val recipe = state.recipe ?: return

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 30.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = "Back"
                    )
                }

                Box {
                    IconButton(onClick = { onAction(IngredientAction.ToggleMoreOptions) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.union),
                            contentDescription = "More options"
                        )
                    }
                    DropdownMenu(
                        expanded = state.isMoreOptionsOpen,
                        onDismissRequest = { onAction(IngredientAction.ToggleMoreOptions) },
                        modifier = Modifier.background(Color.White)
                            .padding(10.dp),
                        offset = DpOffset(0.dp, (-15).dp)
                    ) {
                        DropdownMenuItem(
                            text = { Text("Share", style = AppTextStyles.smallTextRegular) },
                            onClick = { onAction(IngredientAction.ToggleShareDialog) },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.share_black),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = AppColors.black
                                )
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Rate Recipe", style = AppTextStyles.smallTextRegular) },
                            onClick = { /* TODO */ },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.star_black),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = AppColors.black
                                )
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Review", style = AppTextStyles.smallTextRegular) },
                            onClick = { /* TODO */ },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.review_black),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = AppColors.black
                                )
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Unsave", style = AppTextStyles.smallTextRegular) },
                            onClick = { /* TODO */ },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.bookmark_black),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = AppColors.black
                                )
                            }
                        )
                    }
                }
            }
            RecipeCard(
                modifier = Modifier.fillMaxWidth(),
                recipe = recipe,
                showRecipeName = false,
                showChefName = false,
                onClick = {},
                isBookmarked = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = recipe.name,
                    style = AppTextStyles.smallTextBold,
                    color = AppColors.black
                )
                Spacer(modifier = Modifier.width(18.dp))
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = "(13k Reviews)",
                    style = AppTextStyles.labelTextRegular,
                    color = AppColors.gray3
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Chef profile picture",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                            contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(text = recipe.chef, style = AppTextStyles.normalTextBold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.location),
                                contentDescription = "Location",
                                modifier = Modifier.size(14.dp),
                                tint = AppColors.primary80
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Lagos, Nigeria",
                                style = AppTextStyles.smallTextRegular2,
                                color = AppColors.gray3
                            )
                        }
                    }
                }
                SmallButton(
                    modifier = Modifier.width(90.dp),
                    text = "Follow",
                    onClick = { /*TODO*/ },
                    isSelected = true
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SmallButton(
                    modifier = Modifier.weight(1f),
                    text = "Ingredient",
                    onClick = { onAction(IngredientAction.TabSelected("Ingredient")) },
                    isSelected = state.selectedTab == "Ingredient"
                )


                SmallButton(
                    modifier = Modifier.weight(1f),
                    text = "Procedure",
                    onClick = { onAction(IngredientAction.TabSelected("Procedure")) },
                    isSelected = state.selectedTab == "Procedure"
                )
            }
            Spacer(modifier = Modifier.height(35.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.serve),
                    contentDescription = "Serve",
                    tint = AppColors.gray3
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "1 serve", color = AppColors.gray3)
                Spacer(modifier = Modifier.weight(1f))
                val itemsText = if (state.selectedTab == "Ingredient") {
                    "${recipe.ingredients.size} Items"
                } else {
                    "${state.procedures.size} Steps"
                }
                Text(text = itemsText, color = AppColors.gray3)
            }
            Spacer(modifier = Modifier.height(20.dp))

            if (state.selectedTab == "Ingredient") {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(recipe.ingredients) { ingredient ->
                        IngredientItem(recipeIngredient = ingredient)
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.procedures) { procedure ->
                        ProcedureStepItem(
                            stepNumber = procedure.step,
                            stepDescription = procedure.description
                        )
                    }
                }
            }
        }

        // Overlay for Dimming
        if (state.isMoreOptionsOpen || state.isShareDialogVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        if (state.isMoreOptionsOpen) {
                            onAction(IngredientAction.ToggleMoreOptions)
                        } else if (state.isShareDialogVisible) {
                            onAction(IngredientAction.ToggleShareDialog)
                        }
                    }
            )
        }

        if (state.isShareDialogVisible) {
            Popup(
                onDismissRequest = { onAction(IngredientAction.ToggleShareDialog) },
                alignment = Alignment.Center,
                properties = PopupProperties(focusable = true)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(Color.White, shape = RoundedCornerShape(24.dp))
                        .padding(24.dp)
                ) {
                    Column {
                        Text(
                            text = "Recipe Link",
                            style = AppTextStyles.largeTextBold,
                            color = AppColors.black
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Copy recipe link and share your recipe link with  friends and family.",
                            style = AppTextStyles.smallTextRegular,
                            color = AppColors.gray2
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(AppColors.gray4, shape = RoundedCornerShape(9.dp))
                                .padding(start = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = state.recipeLink,
                                style = AppTextStyles.smallTextRegular2,
                                color = AppColors.black,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Button(
                                onClick = { onAction(IngredientAction.CopyLink) },
                                modifier = Modifier.height(44.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = AppColors.primary100)
                            ) {
                                Text(
                                    text = "Copy Link",
                                    style = AppTextStyles.smallerTextBold,
                                    color = AppColors.white
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
fun IngredientShareDialogPreview() {
    val sampleRecipe = Recipe(
        id = 1,
        category = "Indian",
        name = "Spicy chicken burger with French fries",
        image = "https://images.unsplash.com/photo-1598515213692-5f2824124933?q=80&w=2070&auto=format&fit=crop",
        chef = "Laura Wilson",
        time = "20 min",
        rating = 4.0,
        ingredients = emptyList()
    )
    IngredientScreen(
        state = IngredientState(
            recipe = sampleRecipe,
            isShareDialogVisible = true,
            recipeLink = "app.foodrecipe.com/recipe/cpis92ndp"
        ),
        onAction = {},
        onBack = {}
    )
}
