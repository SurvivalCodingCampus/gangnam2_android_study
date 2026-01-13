package com.survivalcoding.gangnam2kiandroidstudy.presentation.fragment.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.databinding.FragmentRecipeDetailBinding
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient.IngredientViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class RecipeDetailFragment : Fragment(R.layout.fragment_recipe_detail) {

    private val viewModel: IngredientViewModel by viewModel()

    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // fragment_recipe_detail.xml과 ViewBinding 연결
        _binding = FragmentRecipeDetailBinding.bind(view)

        // 5) arguments에서 recipeId 꺼내기
        val recipeId = arguments?.getLong(ARG_RECIPE_ID)
            ?: error("recipeId is required")

        // 6) ViewModel에 recipeId 전달 → 상세 데이터 로드하여 state 업데이트
        viewModel.loadRecipeDetail(recipeId)

        // state 관찰 후 UI 반영
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    val recipe = state.recipe
                    binding.tvDetail.text = "id: ${recipe.id}\n" +
                            "name: ${recipe.name}\n" +
                            "chef: ${recipe.chef}\n"
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        // Bundle key는 Fragment 내부에 상수로 정의
        const val ARG_RECIPE_ID = "arg_recipe_id"
    }
}