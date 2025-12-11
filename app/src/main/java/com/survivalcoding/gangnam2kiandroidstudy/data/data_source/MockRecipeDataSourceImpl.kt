package com.survivalcoding.gangnam2kiandroidstudy.data.data_source

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDto


class MockRecipeDataSourceImpl : RecipeDataSource {

    private val mockRecipeDtoList = listOf(
        RecipeDto(
            id = 1,
            category = "Cereal",
            name = "Traditional spare ribs baked",
            image = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            chef = "Chef John",
            time = "20 min",
            rating = 4.0,
            createdAt = 1736034600000, // 2025-01-05
        ),
        RecipeDto(
            id = 2,
            category = "Lunch",
            name = "Spice roasted chicken with flavored rice",
            image = "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
            chef = "Mark Kelvin",
            time = "20 min",
            rating = 4.0,
            createdAt = 1739332200000, // 2025-02-12
        ),
        RecipeDto(
            id = 3,
            category = "Chinese",
            name = "Spicy fried rice mix chicken bali",
            image = "https://cdn.pixabay.com/photo/2019/09/07/19/02/spanish-paella-4459519_1280.jpg",
            chef = "Spicy Nelly",
            time = "20 min",
            rating = 4.0,
            createdAt = 1742453100000, // 2025-03-20
        ),
        RecipeDto(
            id = 4,
            category = "Dinner",
            name = "Ttekbokki",
            image = "https://cdn.pixabay.com/photo/2017/07/27/16/48/toppokki-2545943_1280.jpg",
            chef = "Kim Dahee",
            time = "30 min",
            rating = 5.0,
            createdAt = 1744113720000, // 2025-04-08
        ),
        RecipeDto(
            id = 5,
            category = "Dinner",
            name = "Grilled salmon with avocado salsa",
            image = "https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_1280.jpg",
            chef = "Alice Johnson",
            time = "25 min",
            rating = 4.5,
            createdAt = 1747445700000, // 2025-05-17
        ),
        RecipeDto(
            id = 6,
            category = "Local Dish",
            name = "Beef Wellington",
            image = "https://cdn.pixabay.com/photo/2019/10/22/10/11/beef-wellington-4568239_1280.jpg",
            chef = "Gordon Ramsay",
            time = "45 min",
            rating = 5.0,
            createdAt = 1750863000000, // 2025-06-25
        ),
        RecipeDto(
            id = 7,
            category = "Local Dish",
            name = "Classic Margherita Pizza",
            image = "https://cdn.pixabay.com/photo/2019/05/15/18/56/pizza-4205701_1280.jpg",
            chef = "Mario Batali",
            time = "15 min",
            rating = 4.3,
            createdAt = 1753852800000, // 2025-07-30
        ),
        RecipeDto(
            id = 8,
            category = "Lunch",
            name = "Sushi Platter",
            image = "https://cdn.pixabay.com/photo/2017/10/15/11/41/sushi-2853382_1280.jpg",
            chef = "Jiro Ono",
            time = "60 min",
            rating = 4.8,
            createdAt = 1755168900000, // 2025-08-14
        ),
        RecipeDto(
            id = 9,
            category = "Breakfast",
            name = "French Onion Soup",
            image = "https://cdn.pixabay.com/photo/2016/03/03/16/19/food-1234483_1280.jpg",
            chef = "Julia Child",
            time = "40 min",
            rating = 4.6,
            createdAt = 1758921900000, // 2025-09-27
        ),
        RecipeDto(
            id = 10,
            category = "Cereal",
            name = "Chocolate Lava Cake",
            image = "https://cdn.pixabay.com/photo/2016/11/22/18/52/cake-1850011_1280.jpg",
            chef = "Paul Hollywood",
            time = "30 min",
            rating = 4.9,
            createdAt = 1761941400000, // 2025-10-31
        )
    )

    override suspend fun getRecipes(): List<RecipeDto> {
        return mockRecipeDtoList
    }
}
