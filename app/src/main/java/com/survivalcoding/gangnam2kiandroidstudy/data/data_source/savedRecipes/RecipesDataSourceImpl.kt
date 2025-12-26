package com.survivalcoding.gangnam2kiandroidstudy.data.data_source.savedRecipes

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipes
import kotlinx.serialization.json.Json

class RecipesDataSourceImpl : RecipesDataSource {
    override fun getAllRecipes(): Recipes {
        val emptyFile = """{
  "recipes": []
}"""
        val file = """{
  "recipes": [
    {
      "id": 1,
      "category": "Indian",
      "name": "Traditional spare ribs baked",
      "image": "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
      "chef": "Chef John",
      "time": "20 min",
      "rating": 4.0,
      "isSaved": true,
      "ingredients": [
        {
          "ingredient": {
            "id": 3,
            "name": "Pork",
            "image": "https://cdn.pixabay.com/photo/2019/12/20/14/44/meat-4708596_1280.jpg"
          },
          "amount": 500
        },
        {
          "ingredient": {
            "id": 9,
            "name": "Onion",
            "image": "https://cdn.pixabay.com/photo/2013/02/21/19/14/onion-bulbs-84722_1280.jpg"
          },
          "amount": 50
        },
        {
          "ingredient": {
            "id": 8,
            "name": "Pepper",
            "image": "https://cdn.pixabay.com/photo/2016/03/05/22/31/pepper-1239308_1280.jpg"
          },
          "amount": 10
        },
        {
          "ingredient": {
            "id": 1,
            "name": "Tomato",
            "image": "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg"
          },
          "amount": 100
        }
      ]
    },
    {
      "id": 2,
      "category": "Asian",
      "name": "Spice roasted chicken with flavored rice",
      "image": "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
      "chef": "Mark Kelvin",
      "time": "20 min",
      "rating": 4.0,
      "isSaved": true,
      "ingredients": [
        {
          "ingredient": {
            "id": 6,
            "name": "Chicken",
            "image": "https://cdn.pixabay.com/photo/2010/12/10/08/chicken-1140_1280.jpg"
          },
          "amount": 300
        },
        {
          "ingredient": {
            "id": 4,
            "name": "Rice",
            "image": "https://cdn.pixabay.com/photo/2016/02/29/05/46/brown-rice-1228099_1280.jpg"
          },
          "amount": 200
        },
        {
          "ingredient": {
            "id": 8,
            "name": "Pepper",
            "image": "https://cdn.pixabay.com/photo/2016/03/05/22/31/pepper-1239308_1280.jpg"
          },
          "amount": 5
        },
        {
          "ingredient": {
            "id": 3,
            "name": "Pork",
            "image": "https://cdn.pixabay.com/photo/2019/12/20/14/44/meat-4708596_1280.jpg"
          },
          "amount": 500
        }
      ]
    },
    {
      "id": 3,
      "category": "Chinese",
      "name": "Spicy fried rice mix chicken bali",
      "image": "https://cdn.pixabay.com/photo/2019/09/07/19/02/spanish-paella-4459519_1280.jpg",
      "chef": "Spicy Nelly",
      "time": "20 min",
      "rating": 4.0,
      "isSaved": true,
      "ingredients": [
        {
          "ingredient": {
            "id": 6,
            "name": "Chicken",
            "image": "https://cdn.pixabay.com/photo/2010/12/10/08/chicken-1140_1280.jpg"
          },
          "amount": 200
        },
        {
          "ingredient": {
            "id": 4,
            "name": "Rice",
            "image": "https://cdn.pixabay.com/photo/2016/02/29/05/46/brown-rice-1228099_1280.jpg"
          },
          "amount": 150
        },
        {
          "ingredient": {
            "id": 1,
            "name": "Tomato",
            "image": "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg"
          },
          "amount": 100
        }
      ]
    },
    {
      "id": 4,
      "category": "Japanese",
      "name": "Ttekbokki",
      "image": "https://cdn.pixabay.com/photo/2017/07/27/16/48/toppokki-2545943_1280.jpg",
      "chef": "Kim Dahee",
      "time": "30 min",
      "rating": 5.0,
      "isSaved": true,
      "ingredients": []
    },
    {
      "id": 5,
      "category": "American",
      "name": "Grilled salmon with avocado salsa",
      "image": "https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_1280.jpg",
      "chef": "Alice Johnson",
      "time": "25 min",
      "rating": 4.5,
      "isSaved": true,
      "ingredients": [
        {
          "ingredient": {
            "id": 5,
            "name": "Avocado",
            "image": "https://cdn.pixabay.com/photo/2020/01/02/01/43/avocado-4734786_1280.jpg"
          },
          "amount": 150
        },
        {
          "ingredient": {
            "id": 8,
            "name": "Pepper",
            "image": "https://cdn.pixabay.com/photo/2016/03/05/22/31/pepper-1239308_1280.jpg"
          },
          "amount": 5
        }
      ]
    },
    {
      "id": 6,
      "category": "British",
      "name": "Beef Wellington",
      "image": "https://cdn.pixabay.com/photo/2019/10/22/10/11/beef-wellington-4568239_1280.jpg",
      "chef": "Gordon Ramsay",
      "time": "45 min",
      "rating": 5.0,
      "isSaved": true,
      "ingredients": [
        {
          "ingredient": {
            "id": 2,
            "name": "Beef",
            "image": "https://cdn.pixabay.com/photo/2016/01/21/18/08/meet-1154341_1280.png"
          },
          "amount": 500
        },
        {
          "ingredient": {
            "id": 9,
            "name": "Onion",
            "image": "https://cdn.pixabay.com/photo/2013/02/21/19/14/onion-bulbs-84722_1280.jpg"
          },
          "amount": 100
        }
      ]
    },
    {
      "id": 7,
      "category": "Italian",
      "name": "Classic Margherita Pizza",
      "image": "https://cdn.pixabay.com/photo/2019/05/15/18/56/pizza-4205701_1280.jpg",
      "chef": "Mario Batali",
      "time": "15 min",
      "rating": 4.3,
      "isSaved": true,
      "ingredients": [
        {
          "ingredient": {
            "id": 1,
            "name": "Tomato",
            "image": "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg"
          },
          "amount": 200
        }
      ]
    },
    {
      "id": 8,
      "category": "Japanese",
      "name": "Sushi Platter",
      "image": "https://cdn.pixabay.com/photo/2017/10/15/11/41/sushi-2853382_1280.jpg",
      "chef": "Jiro Ono",
      "time": "60 min",
      "rating": 4.8,
      "isSaved": true,
      "ingredients": [
        {
          "ingredient": {
            "id": 6,
            "name": "Chicken",
            "image": "https://cdn.pixabay.com/photo/2010/12/10/08/chicken-1140_1280.jpg"
          },
          "amount": 200
        },
        {
          "ingredient": {
            "id": 4,
            "name": "Rice",
            "image": "https://cdn.pixabay.com/photo/2016/02/29/05/46/brown-rice-1228099_1280.jpg"
          },
          "amount": 100
        }
      ]
    },
    {
      "id": 9,
      "category": "French",
      "name": "French Onion Soup",
      "image": "https://cdn.pixabay.com/photo/2016/03/03/16/19/food-1234483_1280.jpg",
      "chef": "Julia Child",
      "time": "40 min",
      "rating": 4.6,
      "isSaved": true,
      "ingredients": [
        {
          "ingredient": {
            "id": 9,
            "name": "Onion",
            "image": "https://cdn.pixabay.com/photo/2013/02/21/19/14/onion-bulbs-84722_1280.jpg"
          },
          "amount": 300
        }
      ]
    },
    {
      "id": 10,
      "category": "French",
      "name": "Chocolate Lava Cake",
      "image": "https://cdn.pixabay.com/photo/2016/11/22/18/52/cake-1850011_1280.jpg",
      "chef": "Paul Hollywood",
      "time": "30 min",
      "rating": 4.9,
      "isSaved": true,
      "ingredients": [
        {
          "ingredient": {
            "id": 7,
            "name": "Sugar",
            "image": "https://cdn.pixabay.com/photo/2014/11/28/19/10/lump-sugar-549096_1280.jpg"
          },
          "amount": 100
        },
        {
          "ingredient": {
            "id": 11,
            "name": "Dark Chocolate",
            "image": "https://cdn.pixabay.com/photo/2015/06/24/13/32/chocolate-820127_1280.jpg"
          },
          "amount": 150
        },
        {
          "ingredient": {
            "id": 12,
            "name": "Butter",
            "image": "https://cdn.pixabay.com/photo/2016/01/21/18/01/butter-1154298_1280.jpg"
          },
          "amount": 110
        },
        {
          "ingredient": {
            "id": 13,
            "name": "Egg",
            "image": "https://cdn.pixabay.com/photo/2017/02/19/15/22/eggs-2080072_1280.jpg"
          },
          "amount": 150
        },
        {
          "ingredient": {
            "id": 14,
            "name": "Flour",
            "image": "https://cdn.pixabay.com/photo/2016/09/13/08/58/wheat-1666701_1280.jpg"
          },
          "amount": 50
        },
        {
          "ingredient": {
            "id": 15,
            "name": "Vanilla Extract",
            "image": "https://cdn.pixabay.com/photo/2017/11/12/12/58/vanilla-2942417_1280.jpg"
          },
          "amount": 5
        }
      ]
    }
  ]
}"""

        val json = Json {
            ignoreUnknownKeys = true
        }
        return json.decodeFromString(file)

    }

    override fun deleteSavedRecipe(id: Int) {
        //TODO 나중에 서버 생기면 서버에 isSave를 false로 변경시키게 요청하기
    }
}