package com.survivalcoding.gangnam2kiandroidstudy.data.recipe.datasource

import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.dto.ProcedureDTO

class ProcedureDataSourceImpl : ProcedureDataSource {
    private val procedures = listOf(
        ProcedureDTO(recipeId = 1, step = 1, content = "Preheat the oven to 350°F (175°C)."),
        ProcedureDTO(recipeId = 1, step = 2, content = "Remove the membrane from the back of the ribs if it is still attached."),
        ProcedureDTO(recipeId = 1, step = 3, content = "Season the ribs generously with salt, pepper, and your favorite rub."),
        ProcedureDTO(recipeId = 1, step = 4, content = "Wrap the ribs tightly in aluminum foil to retain moisture."),
        ProcedureDTO(recipeId = 1, step = 5, content = "Place the wrapped ribs on a baking sheet and bake in the preheated oven for 2 hours."),
        ProcedureDTO(recipeId = 1, step = 6, content = "Remove the ribs from the oven and carefully unwrap them."),
        ProcedureDTO(recipeId = 1, step = 7, content = "Brush the ribs with your favorite barbecue sauce."),
        ProcedureDTO(recipeId = 1, step = 8, content = "Return the ribs to the oven, uncovered, and bake for an additional 30 minutes, or until the sauce has caramelized."),

        ProcedureDTO(recipeId = 2, step = 1, content = "Marinate the chicken with spices and let it sit for 30 minutes."),
        ProcedureDTO(recipeId = 2, step = 2, content = "Preheat the oven to 375°F (190°C) and roast the chicken for 45 minutes."),
        ProcedureDTO(recipeId = 2, step = 3, content = "Cook the rice with chicken broth and add vegetables for flavor."),

        ProcedureDTO(recipeId = 3, step = 1, content = "Cook the rice and let it cool."),
        ProcedureDTO(recipeId = 3, step = 2, content = "Stir-fry chicken with spices until cooked through."),
        ProcedureDTO(recipeId = 3, step = 3, content = "Mix the chicken with the rice and vegetables, stir-fry for another 5 minutes."),

        ProcedureDTO(recipeId = 4, step = 1, content = "Soak rice cakes in water for 20 minutes."),
        ProcedureDTO(recipeId = 4, step = 2, content = "Boil water and add gochujang (Korean red chili paste)."),
        ProcedureDTO(recipeId = 4, step = 3, content = "Add rice cakes and fish cakes, cook until the sauce thickens.")
    )

    override suspend fun getProcedures(): List<ProcedureDTO> {
        return procedures
    }
}