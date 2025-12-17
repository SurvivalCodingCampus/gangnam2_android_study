package com.survivalcoding.gangnam2kiandroidstudy.data.data_source

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.ChefDto

class MockChefDataSourceImpl : ChefDataSource {
    
    private val chefs = listOf(
        ChefDto(
            id = 1,
            name = "Chef John",
            image = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            address = "Lagos, Nigeria"
        ),
        ChefDto(
            id = 2,
            name = "Mark Kelvin",
            image = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            address = "Abuja, Nigeria"
        ),
        ChefDto(
            id = 3,
            name = "Spicy Nelly",
            image = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            address = "Ibadan, Nigeria"
        ),
        ChefDto(
            id = 4,
            name = "Kim Dahee",
            image = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            address = "Seoul, South Korea"
        ),
        ChefDto(
            id = 5,
            name = "Alice Johnson",
            image = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            address = "New York, USA"
        ),
        ChefDto(
            id = 6,
            name = "Gordon Ramsay",
            image = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            address = "London, UK"
        ),
        ChefDto(
            id = 7,
            name = "Mario Batali",
            image = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            address = "Rome, Italy"
        ),
        ChefDto(
            id = 8,
            name = "Jiro Ono",
            image = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            address = "Tokyo, Japan"
        ),
        ChefDto(
            id = 9,
            name = "Julia Child",
            image = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            address = "Paris, France"
        ),
        ChefDto(
            id = 10,
            name = "Paul Hollywood",
            image = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
            address = "London, UK"
        )
    )

    override suspend fun getChefs(): List<ChefDto> {
        return chefs
    }

    override suspend fun getChefById(id: Int): ChefDto? {
        return chefs.firstOrNull { it.id == id }
    }
}
