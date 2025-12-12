package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.IngredientDto
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.IngredientListDto
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDto
import com.survivalcoding.gangnam2kiandroidstudy.data.util.date


class MockRecipeDataSourceImpl : RecipeDataSource {
    val mockRecipeDtoList = listOf(
        RecipeDto(
            category = "Italian",
            chef = "Chef John",
            id = 1,
            image = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            name = "Traditional spare ribs baked",
            rating = 4.0,
            time = "20 min",
            createdAt = date("2025-12-10"),
            ingredients = listOf(
                IngredientListDto(
                    amount = 500,
                    ingredient = IngredientDto(
                        id = 3,
                        name = "Pork",
                        image = "https://cdn.pixabay.com/photo/2019/12/20/14/44/meat-4708596_1280.jpg"
                    )
                ),
                IngredientListDto(
                    amount = 50,
                    ingredient = IngredientDto(
                        id = 9,
                        name = "Onion",
                        image = "https://cdn.pixabay.com/photo/2013/02/21/19/14/onion-bulbs-84722_1280.jpg"
                    )
                ),
                IngredientListDto(
                    amount = 10,
                    ingredient = IngredientDto(
                        id = 8,
                        name = "Pepper",
                        image = "https://cdn.pixabay.com/photo/2016/03/05/22/31/pepper-1239308_1280.jpg"
                    )
                ),
                IngredientListDto(
                    amount = 100,
                    ingredient = IngredientDto(
                        id = 1,
                        name = "Tomato",
                        image = "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg"
                    )
                )
            )
        ),
        RecipeDto(
            category = "Local Dishes",
            chef = "Mark Kelvin",
            id = 2,
            image = "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
            name = "Spice roasted chicken with flavored rice",
            rating = 4.0,
            time = "20 min",
            createdAt = date("2000-03-10"),
            ingredients = listOf(
                IngredientListDto(
                    amount = 300,
                    ingredient = IngredientDto(
                        id = 6,
                        name = "Chicken",
                        image = "https://cdn.pixabay.com/photo/2010/12/10/08/chicken-1140_1280.jpg"
                    )
                ),
                IngredientListDto(
                    amount = 200,
                    ingredient = IngredientDto(
                        id = 4,
                        name = "Rice",
                        image = "https://cdn.pixabay.com/photo/2016/02/29/05/46/brown-rice-1228099_1280.jpg"
                    )
                ),
                IngredientListDto(
                    amount = 5,
                    ingredient = IngredientDto(
                        id = 8,
                        name = "Pepper",
                        image = "https://cdn.pixabay.com/photo/2016/03/05/22/31/pepper-1239308_1280.jpg"
                    )
                ),
                IngredientListDto(
                    amount = 500,
                    ingredient = IngredientDto(
                        id = 3,
                        name = "Pork",
                        image = "https://cdn.pixabay.com/photo/2019/12/20/14/44/meat-4708596_1280.jpg"
                    )
                )
            )
        ),
        RecipeDto(
            category = "Chinese",
            chef = "Spicy Nelly",
            id = 3,
            image = "https://cdn.pixabay.com/photo/2019/09/07/19/02/spanish-paella-4459519_1280.jpg",
            name = "Spicy fried rice mix chicken bali",
            rating = 4.0,
            time = "20 min",
            createdAt = date("2020-01-10"),
            ingredients = listOf(
                IngredientListDto(
                    amount = 200,
                    ingredient = IngredientDto(
                        id = 6,
                        name = "Chicken",
                        image = "https://cdn.pixabay.com/photo/2010/12/10/08/chicken-1140_1280.jpg"
                    )
                ),
                IngredientListDto(
                    amount = 150,
                    ingredient = IngredientDto(
                        id = 4,
                        name = "Rice",
                        image = "https://cdn.pixabay.com/photo/2016/02/29/05/46/brown-rice-1228099_1280.jpg"
                    )
                ),
                IngredientListDto(
                    amount = 100,
                    ingredient = IngredientDto(
                        id = 1,
                        name = "Tomato",
                        image = "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg"
                    )
                )
            )
        ),
        RecipeDto(
            category = "Asian",
            chef = "Kim Dahee",
            id = 4,
            image = "https://cdn.pixabay.com/photo/2017/07/27/16/48/toppokki-2545943_1280.jpg",
            name = "Ttekbokki",
            rating = 5.0,
            time = "30 min",
            ingredients = emptyList(),
            createdAt = date("2025-12-06"),
        ),
        RecipeDto(
            category = "Italian",
            chef = "Alice Johnson",
            id = 5,
            image = "https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_1280.jpg",
            name = "Grilled salmon with avocado salsa",
            rating = 4.5,
            time = "25 min",
            createdAt = date("2020-01-01"),
            ingredients = listOf(
                IngredientListDto(
                    amount = 150,
                    ingredient = IngredientDto(
                        id = 5,
                        name = "Avocado",
                        image = "https://cdn.pixabay.com/photo/2020/01/02/01/43/avocado-4734786_1280.jpg"
                    )
                ),
                IngredientListDto(
                    amount = 5,
                    ingredient = IngredientDto(
                        id = 8,
                        name = "Pepper",
                        image = "https://cdn.pixabay.com/photo/2016/03/05/22/31/pepper-1239308_1280.jpg"
                    )
                )
            )
        ),
        RecipeDto(
            category = "Italian",
            chef = "Gordon Ramsay",
            id = 6,
            image = "https://cdn.pixabay.com/photo/2019/10/22/10/11/beef-wellington-4568239_1280.jpg",
            name = "Beef Wellington",
            rating = 5.0,
            time = "45 min",
            createdAt = date("2023-02-10"),
            ingredients = listOf(
                IngredientListDto(
                    amount = 500,
                    ingredient = IngredientDto(
                        id = 2,
                        name = "Beef",
                        image = "https://cdn.pixabay.com/photo/2016/01/21/18/08/meet-1154341_1280.png"
                    )
                ),
                IngredientListDto(
                    amount = 100,
                    ingredient = IngredientDto(
                        id = 9,
                        name = "Onion",
                        image = "https://cdn.pixabay.com/photo/2013/02/21/19/14/onion-bulbs-84722_1280.jpg"
                    )
                )
            )
        ),
        RecipeDto(
            category = "Italian",
            chef = "Mario Batali",
            id = 7,
            image = "https://cdn.pixabay.com/photo/2019/05/15/18/56/pizza-4205701_1280.jpg",
            name = "Classic Margherita Pizza",
            rating = 4.3,
            time = "15 min",
            createdAt = date("2025-09-12"),
            ingredients = listOf(
                IngredientListDto(
                    amount = 200,
                    ingredient = IngredientDto(
                        id = 1,
                        name = "Tomato",
                        image = "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg"
                    )
                )
            )
        ),
        RecipeDto(
            category = "Asian",
            chef = "Jiro Ono",
            id = 8,
            image = "https://cdn.pixabay.com/photo/2017/10/15/11/41/sushi-2853382_1280.jpg",
            name = "Sushi Platter",
            rating = 4.8,
            time = "60 min",
            createdAt = date("2025-12-11"),
            ingredients = listOf(
                IngredientListDto(
                    amount = 200,
                    ingredient = IngredientDto(
                        id = 6,
                        name = "Chicken",
                        image = "https://cdn.pixabay.com/photo/2010/12/10/08/chicken-1140_1280.jpg"
                    )
                ),
                IngredientListDto(
                    amount = 100,
                    ingredient = IngredientDto(
                        id = 4,
                        name = "Rice",
                        image = "https://cdn.pixabay.com/photo/2016/02/29/05/46/brown-rice-1228099_1280.jpg"
                    )
                )
            )
        ),
        RecipeDto(
            category = "Cereal",
            chef = "Julia Child",
            id = 9,
            image = "https://cdn.pixabay.com/photo/2016/03/03/16/19/food-1234483_1280.jpg",
            name = "French Onion Soup",
            rating = 4.6,
            time = "40 min",
            createdAt = date("2025-12-09"),
            ingredients = listOf(
                IngredientListDto(
                    amount = 300,
                    ingredient = IngredientDto(
                        id = 9,
                        name = "Onion",
                        image = "https://cdn.pixabay.com/photo/2013/02/21/19/14/onion-bulbs-84722_1280.jpg"
                    )
                )
            )
        ),
        RecipeDto(
            category = "Italian",
            chef = "Paul Hollywood",
            id = 10,
            image = "https://cdn.pixabay.com/photo/2016/11/22/18/52/cake-1850011_1280.jpg",
            name = "Chocolate Lava Cake",
            rating = 4.9,
            time = "30 min",
            createdAt = date("2025-05-10"),
            ingredients = listOf(
                IngredientListDto(
                    amount = 100,
                    ingredient = IngredientDto(
                        id = 7,
                        name = "Sugar",
                        image = "https://cdn.pixabay.com/photo/2014/11/28/19/10/lump-sugar-549096_1280.jpg"
                    )
                )
            )
        )
    )

    override suspend fun getRecipes(): List<RecipeDto> {
        return mockRecipeDtoList
    }
}