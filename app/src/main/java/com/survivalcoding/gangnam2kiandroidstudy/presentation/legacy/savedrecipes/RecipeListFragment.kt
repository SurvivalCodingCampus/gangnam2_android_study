package com.survivalcoding.gangnam2kiandroidstudy.presentation.legacy.savedrecipes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.databinding.FragmentRecipeListBinding
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes.SavedRecipesAction
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes.SavedRecipesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeListFragment : Fragment(R.layout.fragment_recipe_list) {

    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SavedRecipesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRecipeListBinding.bind(view)

        val adapter = RecipeRecyclerAdapter(
            object : SavedRecipesActionListener {
                override fun onCardClick(recipeId: Long) {
                    /*
                    RecipeDetailFragment 로 전환
                     */
                    parentFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container,
                            RecipeDetailFragment.newInstance(recipeId),
                        )
                        .addToBackStack(null) // 뒤로가기 지원
                        .commit()
                }

                override fun onBookmarkClick(recipeId: Long) {
                    viewModel.onAction(SavedRecipesAction.OnBookmarkClick(recipeId))
                }
            },
        )
        binding.recyclerView.adapter = adapter

        /*
        View 의 라이프사이클에 따르는 scope
         */
        viewLifecycleOwner.lifecycleScope.launch {
            /*
            화면이 보일 때만 컬렉트 하도록 사용
             */
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    adapter.updateData(it.recipes)
                }
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
}