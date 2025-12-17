package com.survivalcoding.gangnam2kiandroidstudy.data.data_source

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.ProcedureDto
import javax.inject.Inject

class MockProcedureDataSourceImpl @Inject constructor() : ProcedureDataSource {

    private val procedures = listOf(
        // Recipe 1
        ProcedureDto(1, 1, "Preheat the oven to 350°F (175°C)."),
        ProcedureDto(
            1,
            2,
            "Remove the membrane from the back of the ribs if it is still attached."
        ),
        ProcedureDto(1, 3, "Season the ribs generously with salt, pepper, and your favorite rub."),
        ProcedureDto(1, 4, "Wrap the ribs tightly in aluminum foil to retain moisture."),
        ProcedureDto(
            1,
            5,
            "Place the wrapped ribs on a baking sheet and bake in the preheated oven for 2 hours."
        ),
        ProcedureDto(1, 6, "Remove the ribs from the oven and carefully unwrap them."),
        ProcedureDto(1, 7, "Brush the ribs with your favorite barbecue sauce."),
        ProcedureDto(
            1,
            8,
            "Return the ribs to the oven, uncovered, and bake for an additional 30 minutes, or until the sauce has caramelized."
        ),

        // Recipe 2
        ProcedureDto(
            2,
            1,
            "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        ),
        ProcedureDto(
            2,
            2,
            "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?\n" +
                    "Tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        ),
        ProcedureDto(
            2,
            3,
            "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        ),
        ProcedureDto(2, 4, "Marinate the chicken with spices and let it sit for 30 minutes."),
        ProcedureDto(
            2,
            5,
            "Preheat the oven to 375°F (190°C) and roast the chicken for 45 minutes."
        ),
        ProcedureDto(2, 6, "Cook the rice with chicken broth and add vegetables for flavor."),

        // Recipe 3
        ProcedureDto(3, 1, "Cook the rice and let it cool."),
        ProcedureDto(3, 2, "Stir-fry chicken with spices until cooked through."),
        ProcedureDto(
            3,
            3,
            "Mix the chicken with the rice and vegetables, stir-fry for another 5 minutes."
        ),

        // Recipe 4
        ProcedureDto(4, 1, "Soak rice cakes in water for 20 minutes."),
        ProcedureDto(4, 2, "Boil water and add gochujang (Korean red chili paste)."),
        ProcedureDto(4, 3, "Add rice cakes and fish cakes, cook until the sauce thickens.")
    )

    override suspend fun getProceduresByRecipeId(recipeId: Int): List<ProcedureDto> {
        return procedures
            .filter { it.recipeId == recipeId }
            .sortedBy { it.step }   // step 순서 보장
    }
}
