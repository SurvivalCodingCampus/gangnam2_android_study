package com.survivalcoding.gangnam2kiandroidstudy.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory

class RecipeListFragment : Fragment(R.layout.fragment_recipe_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RecipeListAdapter(dummyRecipes())
    }

    private fun dummyRecipes(): List<Recipe> {
        return listOf(
            Recipe(id = 1,
                category = RecipeCategory.DINNER,
                name = "Traditional spare ribs baked",
                imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
                chef = "Chef John",
                time = "20 min",
                rating = 4.0,
                ingredients = listOf()
            ),
            Recipe(id = 2,
                category = RecipeCategory.ASIAN,
                name = "Spice roasted chicken with flavored rice",
                imageUrl = "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
                chef = "Mark Kelvin",
                time = "25 min",
                rating = 4.8,
                ingredients = listOf()
            ),
            Recipe(id = 3,
                category = RecipeCategory.CHINESE,
                name = "Spicy fried rice mix chicken bali",
                imageUrl = "https://cdn.pixabay.com/photo/2019/09/07/19/02/spanish-paella-4459519_1280.jpg",
                chef = "Spicy Nelly",
                time = "20 min",
                rating = 4.2,
                ingredients = listOf()
            ),
            Recipe(id = 4,
                category = RecipeCategory.ASIAN,
                name = "Spicy fried rice mix chicken bali jadjfkjfalldsfweeeoodsfpskljwkdjfsdfdfd",
                imageUrl = "https://cdn.pixabay.com/photo/2019/09/07/19/02/spanish-paella-4459519_1280.jpg",
                chef = "Spicy Nelly",
                time = "20 min",
                rating = 4.0,
                ingredients = listOf()
            ),
            Recipe(id = 5,
                category = RecipeCategory.CEREAL,
                name = "Cereal",
                imageUrl = "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
                chef = "Mark Kelvin",
                time = "5 min",
                rating = 4.8,
                ingredients = listOf()
            ),
        )
    }
}
