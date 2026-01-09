package com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.savedrecipes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.databinding.FragmentRecipeDetailBinding
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail.RecipeDetailsNavigation
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail.RecipeDetailsRoot

private const val RECIPE_ID = "recipeId"

class RecipeDetailFragment : Fragment(R.layout.fragment_recipe_detail) {

    private var recipeId: Long = 0
    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipeId = it.getLong(RECIPE_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRecipeDetailBinding.bind(view)

        /*
        RecipeDetailsRoot 를 사용하기 위한 ComposeView 세팅
         */
        binding.recipeDetailView.apply {
            setContent {
                RecipeDetailsRoot(
                    id = recipeId,
                    onNavigate = { navigation ->
                        when (navigation) {
                            RecipeDetailsNavigation.Back -> {
                                /*
                                FragmentManager 로 뒤로가기
                                 */
                                parentFragmentManager.popBackStack()
                            }
                            is RecipeDetailsNavigation.Reviews -> {}
                        }
                    },
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        /*
        죽을 때 메모리 해제
         */
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(recipeId: Long) =
            RecipeDetailFragment().apply {
                /*
                프래그먼트 라이프사이클에 안전하게 번들에 아이디를 담아서 사용
                 */
                arguments = Bundle().apply {
                    putLong(RECIPE_ID, recipeId)
                }
            }
    }
}