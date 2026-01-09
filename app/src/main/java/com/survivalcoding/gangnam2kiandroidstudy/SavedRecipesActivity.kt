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

        supportFragmentManager.setFragmentResultListener("recipe_selected", this) { _, bundle ->
            val recipeId = bundle.getInt("id")

            // 결과를 받았을 때 화면 전환 로직 실행
            navigateToDetail(recipeId)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null) {
            val recipeListFragment = RecipeListFragment()


            // 초기 RecipeListFragment 연결
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, recipeListFragment)
                .commit()


        }
    }

    private fun navigateToDetail(recipeId: Int) {
        val bundle = bundleOf("id" to recipeId)
        val detailFragment = RecipeDetailFragment().apply {
            arguments = bundle
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}