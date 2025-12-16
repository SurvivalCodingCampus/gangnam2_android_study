package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository

object MockProcedureRepositoryImpl : ProcedureRepository {

    val mockProcedures = listOf(
        Procedure(
            recipeId = 1L,
            step = 1,
            content = "Preheat the oven to 350°F (175°C).",
        ),
        Procedure(
            recipeId = 1L,
            step = 2,
            content = "Remove the membrane from the back of the ribs if it is still attached.",
        ),
        Procedure(
            recipeId = 1L,
            step = 3,
            content = "Season the ribs generously with salt, pepper, and your favorite rub.",
        ),
        Procedure(
            recipeId = 1L,
            step = 4,
            content = "Wrap the ribs tightly in aluminum foil to retain moisture.",
        ),
        Procedure(
            recipeId = 1L,
            step = 5,
            content = "Place the wrapped ribs on a baking sheet and bake in the preheated oven for 2 hours.",
        ),
        Procedure(
            recipeId = 1L,
            step = 6,
            content = "Remove the ribs from the oven and carefully unwrap them.",
        ),
        Procedure(
            recipeId = 1L,
            step = 7,
            content = "Brush the ribs with your favorite barbecue sauce.",
        ),
        Procedure(
            recipeId = 1L,
            step = 8,
            content = "Return the ribs to the oven, uncovered, and bake for an additional 30 minutes, or until the sauce has caramelized.",
        ),
        Procedure(
            recipeId = 2L,
            step = 1,
            content = "Marinate the chicken with spices and let it sit for 30 minutes.",
        ),
        Procedure(
            recipeId = 2L,
            step = 2,
            content = "Preheat the oven to 375°F (190°C) and roast the chicken for 45 minutes.",
        ),
        Procedure(
            recipeId = 2L,
            step = 3,
            content = "Cook the rice with chicken broth and add vegetables for flavor.",
        ),
        Procedure(
            recipeId = 3L,
            step = 1,
            content = "Cook the rice and let it cool.",
        ),
        Procedure(
            recipeId = 3L,
            step = 2,
            content = "Stir-fry chicken with spices until cooked through.",
        ),
        Procedure(
            recipeId = 3L,
            step = 3,
            content = "Mix the chicken with the rice and vegetables, stir-fry for another 5 minutes.",
        ),
        Procedure(
            recipeId = 4L,
            step = 1,
            content = "Soak rice cakes in water for 20 minutes.",
        ),
        Procedure(
            recipeId = 4L,
            step = 2,
            content = "Boil water and add gochujang (Korean red chili paste).",
        ),
        Procedure(
            recipeId = 4L,
            step = 3,
            content = "Add rice cakes and fish cakes, cook until the sauce thickens.",
        ),
    )

    override suspend fun getProceduresByRecipeId(recipeId: Long): List<Procedure> {
        return mockProcedures.filter { it.recipeId == recipeId }
    }
}