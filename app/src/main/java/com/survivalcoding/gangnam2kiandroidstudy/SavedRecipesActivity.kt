package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.survivalcoding.gangnam2kiandroidstudy.databinding.ActivitySavedRecipesBinding
import com.survivalcoding.gangnam2kiandroidstudy.presentation.fragment.DetailFragment
import com.survivalcoding.gangnam2kiandroidstudy.presentation.fragment.RecipeDetailFragment
import com.survivalcoding.gangnam2kiandroidstudy.presentation.fragment.RecipeListFragment

class SavedRecipesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySavedRecipesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySavedRecipesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        if (savedInstanceState == null) {
            val recipeListFragment = RecipeListFragment(
                object : RecipeListFragment.OnRecipeSelectedListener {
                    override fun onRecipeSelected(recipeId: Int) {
                        val bundle = bundleOf("id" to recipeId)
                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.fragment_container, DetailFragment().apply {
//                                arguments = bundle
//                            })
                            .replace(R.id.fragment_container, RecipeDetailFragment().apply {
                                arguments = bundle
                            })
                            .addToBackStack(null)
                            .commit()
                    }
                }
            )
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, recipeListFragment)
                .commit()
        }
    }
}